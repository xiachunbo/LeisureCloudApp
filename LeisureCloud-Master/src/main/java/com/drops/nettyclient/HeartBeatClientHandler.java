package com.drops.nettyclient;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.drops.protoco.MessageBase;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.util.CharsetUtil;
import io.netty.util.ReferenceCountUtil;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;
import io.netty.util.concurrent.GlobalEventExecutor;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;

public class HeartBeatClientHandler extends ChannelInboundHandlerAdapter {
    Logger logger  =  Logger.getLogger(HeartBeatClientHandler.class );

    private static  ByteBuf HEARTBEAT_SEQUENCE;

    private static final int TRY_TIMES = 3;

    private int currentTime = 0;

    public static ChannelGroup channels =new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        //System.out.println("激活时间是："+new Date());
        //加入ChannelGroup
        channels.add(ctx.channel());
        logger.info("HeartBeatClientHandler channelActive"+"[当前连接数:"+channels.size()+"]");
        ctx.fireChannelActive();
        ctx.channel().read();
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        //System.out.println("停止时间是："+new Date());
        //System.out.println("HeartBeatClientHandler channelInactive");
        Thread.currentThread().sleep(3000);
        NettyClient client = new NettyClient(NettyClient.host,NettyClient.port);
        client.connect();
        logger.info("重新建立连接.");

    }

    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if (evt instanceof IdleStateEvent) {
            IdleStateEvent event = (IdleStateEvent) evt;
            if (event.state() == IdleState.WRITER_IDLE) {
                if(currentTime >= 0){
                    //System.out.println("currentTime:"+currentTime);
                    currentTime++;
                    Map<String,String> params=new HashMap<String,String>();
                    params.put("user","crawlerservice");
                    params.put("data","PING...");
                    JSONObject jsonObject= JSONObject.fromObject(params);
                    String jsonStr=jsonObject.toString();
                    HEARTBEAT_SEQUENCE = Unpooled.unreleasableBuffer(Unpooled.copiedBuffer(Unpooled.copiedBuffer(jsonStr,
                            CharsetUtil.UTF_8)));
                    ctx.channel()
                            .writeAndFlush(HEARTBEAT_SEQUENCE.duplicate()).addListener(new GenericFutureListener<Future<? super Void>>() {
                        @Override
                        public void operationComplete(Future<? super Void> future) throws Exception {
                            //消息发送成功
                            if (future.isSuccess()) {
                                //...
                                //System.out.println("isSuccess:"+true);
                            }
                            //消息发送失败
                            else {
                                //ChannelUtils.closeOnFlush(ctx.channel());
                                //System.out.println("isSuccess:"+false);
                            }
                        }
                    });
                }
            }
        }
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        ByteBuf message =(ByteBuf)msg;
        String channel = ctx.channel().toString();
        channel = channel.replaceAll("[\\[\\]]", "").split(",")[0].split(":")[1].trim();
        JSONObject jsonObject= JSONObject.fromObject(convertByteBufToString(message));
        logger.info("收到来自服务端["+jsonObject.get("user")+channel+"]消息:"+jsonObject.get("data"));
        if (message.equals("Heartbeat")) {
            ctx.write("has read message from server");
            ctx.flush();
        }
        ReferenceCountUtil.release(msg);
    }
    public String convertByteBufToString(ByteBuf buf) {
        String str;
        if (buf.hasArray()) { // 处理堆缓冲区
            str = new String(buf.array(), buf.arrayOffset() + buf.readerIndex(), buf.readableBytes());
        } else { // 处理直接缓冲区以及复合缓冲区
            byte[] bytes = new byte[buf.readableBytes()];
            buf.getBytes(buf.readerIndex(), bytes);
            str = new String(bytes, 0, buf.readableBytes());
        }
        return str;
    }
}
