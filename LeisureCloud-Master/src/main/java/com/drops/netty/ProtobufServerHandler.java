package com.drops.netty;

import com.alibaba.fastjson.JSON;
import com.drops.protoco.MessageBase;
import com.drops.tools.MyChannelMap;
import com.drops.tools.SingletonPool;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.util.CharsetUtil;
import io.netty.util.ReferenceCountUtil;
import io.netty.util.concurrent.EventExecutor;
import io.netty.util.concurrent.GlobalEventExecutor;
import org.apache.log4j.Logger;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class ProtobufServerHandler extends SimpleChannelInboundHandler<MessageBase.Message> {
    Logger logger = Logger.getLogger(ProtobufServerHandler.class);
    private int loss_connect_time = 0;
    private static final SimpleDateFormat SDF = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static ByteBuf HEARTBEAT_SEQUENCE;
    private static ChannelGroup channels = (ChannelGroup) new DefaultChannelGroup((EventExecutor) GlobalEventExecutor.INSTANCE);


    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        String channel = ctx.channel().toString();
        channel = channel.replaceAll("[\\[\\]]", "").split(",")[0].split(":")[1].trim();
        if (evt instanceof IdleStateEvent) {
            IdleStateEvent event = (IdleStateEvent) evt;
            if (IdleStateEvent.class.isAssignableFrom(evt.getClass())) {
                IdleStateEvent event1 = (IdleStateEvent) evt;
                if (event1.state() == IdleState.READER_IDLE) {
                    System.out.println("read idle");
                    this.logger.info(40 + "秒没有接收到用户[" + channel + "]的心跳了");
                    this.logger.info("关闭[" + channel + "]这个不活跃的channel");
                    ctx.channel().close();
                    this.logger.info("服务端提醒您:用户[" + channel + "]已被断开[当前连接数" + channels.size() + "]");
                    String node = SingletonPool.currentNodePool.get(channel).toString();
                    SingletonPool.nodePool.remove(node);
                }else if (event1.state() == IdleState.WRITER_IDLE)
                    System.out.println("write idle");
                else if (event1.state() == IdleState.ALL_IDLE)
                    System.out.println("all idle");
            }
        } else {
            super.userEventTriggered(ctx, evt);
        }
    }


    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageBase.Message msg) throws Exception {
        //System.out.println("服务端收到了一数据\n" + msg.toString() + "======================");
        String channel = ctx.channel().toString();
        channel = channel.replaceAll("[\\[\\]]", "").split(",")[0].split(":")[1].trim();
        Set set = SingletonPool.getStance().getChannels().keySet();
        JSONObject obj = new JSONObject(msg.getContent());
        this.logger.info("收到来自用户["+ obj.get("user") + "-- > " + channel + "]消息"+obj.get("data") + "[当前连接数:"+ channels.size() + "]");
        if (obj.get("data").equals("connect")) {
            MyChannelMap.getInstance().getMap().put(obj.get("user").toString(), ctx.channel());
        }
        if (obj.get("data").equals("PING...")) {
            Channel channelData = (Channel) MyChannelMap.getInstance().getMap().get(obj.get("user").toString());
            Map<String, String> params = new HashMap<>();
            params.put("user", "masterservice");
            params.put("data", "心跳检测 PING...");
            String json = JSON.toJSON(params).toString();
            HEARTBEAT_SEQUENCE = Unpooled.unreleasableBuffer(Unpooled.copiedBuffer(Unpooled.copiedBuffer(json, CharsetUtil.UTF_8)));
            MessageBase.Message.Builder message = new MessageBase.Message.Builder();
            message.setRequestId("1");
            message.setContent(json);
            //channelData.writeAndFlush(HEARTBEAT_SEQUENCE.duplicate());
            channelData.writeAndFlush(message.build());
        }
        if (obj.get("data").toString().contains(":") && !obj.get("data").toString().contains("getway") &&
                !SingletonPool.nodePool.contains(obj.get("data").toString())) {
            SingletonPool.nodePool.add(obj.get("data").toString());
            SingletonPool.currentNodePool.put(channel, obj.get("data").toString());
        }

        if (obj.get("data").toString().contains("getway") &&
                !SingletonPool.getwayPool.contains(obj.get("data").toString())) {
            SingletonPool.getwayPool.add(obj.get("data").toString());
            SingletonPool.currentGetwayPool.put(channel, obj.get("data").toString());
        }

        ReferenceCountUtil.release(msg);
    }

    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        String channel = ctx.channel().toString();
        channel = channel.replaceAll("[\\[\\]]", "").split(",")[0].split(":")[1].trim();
        this.logger.info(channel + "的设备退出数据中心Online"+ channels.size());
        String node = SingletonPool.currentNodePool.get(channel).toString();
        SingletonPool.nodePool.remove(node);
        String getway = SingletonPool.currentGetwayPool.get(channel).toString();
        SingletonPool.getwayPool.remove(getway);
        ctx.close();
        this.logger.info(cause.getMessage());
    }


    public void handlerAdded(ChannelHandlerContext ctx) {
        channels.add(ctx.channel());
        String channel = ctx.channel().toString();
        channel = channel.replaceAll("[\\[\\]]", "").split(",")[0].split(":")[1].trim();
        this.logger.info(channel + "的设备加入数据中心Online"+ channels.size());
    }


    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        String _channel = ctx.channel().toString();
        _channel = _channel.replaceAll("[\\[\\]]", "").split(",")[0].split(":")[1].trim();
        this.logger.info(_channel + "的设备退出数据中心Online"+ channels.size());
        String node = SingletonPool.currentNodePool.get(_channel).toString();
        SingletonPool.nodePool.remove(node);
        String getway = SingletonPool.currentGetwayPool.get(_channel).toString();
        SingletonPool.getwayPool.remove(getway);
    }

    public String convertByteBufToString(ByteBuf buf) {
        String str;
        if (buf.hasArray()) {
            str = new String(buf.array(), buf.arrayOffset() + buf.readerIndex(), buf.readableBytes());
        } else {
            byte[] bytes = new byte[buf.readableBytes()];
            buf.getBytes(buf.readerIndex(), bytes);
            str = new String(bytes, 0, buf.readableBytes());
        }
        return str;
    }

    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        String channel = ctx.channel().toString();
        channel = channel.replaceAll("[\\[\\]]", "").split(",")[0].split(":")[1].trim();
        SingletonPool.getStance().getChannels().put(channel, ctx.channel());
        this.logger.info("[服务端提醒您:用户["+ channel + "]已连接");

        Map<String, String> params = new HashMap<>();
        params.put("user", "masterservice");
        params.put("data", "欢迎加入数据中心");
        String json = JSON.toJSON(params).toString();
        HEARTBEAT_SEQUENCE = Unpooled.unreleasableBuffer(Unpooled.copiedBuffer(Unpooled.copiedBuffer(json, CharsetUtil.UTF_8)));
        MessageBase.Message.Builder message = new MessageBase.Message.Builder();
        message.setRequestId("1");
        message.setContent(json);
        //ctx.channel().writeAndFlush(HEARTBEAT_SEQUENCE.duplicate());
        ctx.channel().writeAndFlush(message.build());
        SingletonPool.currentNodePool.put(channel, "");
        SingletonPool.currentGetwayPool.put(channel, "");
    }
}

