package com.drops.netty;

import com.alibaba.fastjson.JSON;
import com.drops.protoco.MessageBase;
import com.drops.tools.MyChannelMap;
import com.drops.tools.SingletonPool;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.util.CharsetUtil;
import io.netty.util.ReferenceCountUtil;
import io.netty.util.concurrent.EventExecutor;
import io.netty.util.concurrent.GlobalEventExecutor;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.json.JSONObject;

public class HeartbeatServerHandler extends ChannelInboundHandlerAdapter {
    Logger logger = Logger.getLogger(HeartbeatServerHandler.class);
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
            if (event.state() == IdleState.READER_IDLE) {
                this.loss_connect_time++;

                if (this.loss_connect_time > 300) {
                    this.logger.info(this.loss_connect_time + "秒没有接收到用户["+ channel + "]的心跳了");
                    this.logger.info("关闭["+ channel + "]这个不活跃的channel");
                    ctx.channel().close();

                    this.logger.info("服务端提醒您:用户["+ channel + "]已被断开[当前连接数"+channels.size() + "]");
                    String node = SingletonPool.currentNodePool.get(channel).toString();
                    SingletonPool.nodePool.remove(node);
                }
            }
        } else {
            super.userEventTriggered(ctx, evt);
        }
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        // 如果是protobuf类型的数据
        if (msg instanceof MessageBase.Message.Builder) {
            this.logger.info("1111");
        }
        // 如果是ByteBuf类型的数据
        if (msg instanceof ByteBuf) {
            //this.logger.info("22222");
        }
        ByteBuf bf = (ByteBuf) msg;
        String channel = ctx.channel().toString();
        channel = channel.replaceAll("[\\[\\]]", "").split(",")[0].split(":")[1].trim();
        Set set = SingletonPool.getStance().getChannels().keySet();
        JSONObject obj = new JSONObject(convertByteBufToString(bf));
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

            channelData.writeAndFlush(HEARTBEAT_SEQUENCE.duplicate());
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

        ctx.channel().writeAndFlush(HEARTBEAT_SEQUENCE.duplicate());
        SingletonPool.currentNodePool.put(channel, "");
        SingletonPool.currentGetwayPool.put(channel, "");
    }
}
