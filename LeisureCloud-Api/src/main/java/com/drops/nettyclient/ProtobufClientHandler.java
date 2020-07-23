package com.drops.nettyclient;

import com.drops.common.protoco.MessageBase;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.util.ReferenceCountUtil;
import io.netty.util.concurrent.Future;
import io.netty.util.concurrent.GenericFutureListener;
import io.netty.util.concurrent.GlobalEventExecutor;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

public class ProtobufClientHandler extends SimpleChannelInboundHandler<MessageBase.Message> {
    Logger logger = Logger.getLogger(ProtobufClientHandler.class);
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
            if (event.state() == IdleState.READER_IDLE) {
                System.out.println("read idle");
                Map<String,String> params=new HashMap<String,String>();
                params.put("user","LeisureCloud-Api");
                params.put("data","PING...");
                JSONObject jsonObject= JSONObject.fromObject(params);
                String jsonStr=jsonObject.toString();
                MessageBase.Message.Builder message = new MessageBase.Message.Builder();
                message.setRequestId("1");
                message.setContent(jsonStr);
                //HEARTBEAT_SEQUENCE = Unpooled.unreleasableBuffer(Unpooled.copiedBuffer(Unpooled.copiedBuffer(jsonStr,
                //CharsetUtil.UTF_8)));
                ctx.channel()
                        .writeAndFlush(message.build()).addListener(new GenericFutureListener<Future<? super Void>>() {
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
            }else if (event.state() == IdleState.WRITER_IDLE)
                System.out.println("write idle");
            else if (event.state() == IdleState.ALL_IDLE)
                System.out.println("all idle");
        }
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageBase.Message msg) throws Exception {
        //System.out.println("客户端收到了一数据\n" + msg.toString() + "======================");
        String channel = ctx.channel().toString();
        channel = channel.replaceAll("[\\[\\]]", "").split(",")[0].split(":")[1].trim();
        JSONObject jsonObject= JSONObject.fromObject(msg.getContent());
        logger.info("收到来自服务端["+jsonObject.get("user")+channel+"]消息:"+jsonObject.get("data"));
        if (msg.getContent().equals("Heartbeat")) {
            ctx.write("has read message from server");
            ctx.flush();
        }
        ReferenceCountUtil.release(msg);
    }
}

