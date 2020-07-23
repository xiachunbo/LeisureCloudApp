package com.drops.nettyclient;

import com.drops.netty.ProtobufServerHandler;
import com.drops.protoco.MessageBase;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;
import io.netty.handler.timeout.IdleStateHandler;

import java.util.concurrent.TimeUnit;

public class HeartbeatHandlerClientInitializer
        extends ChannelInitializer<Channel> {
    private final static int readerIdleTimeSeconds = 30;//读操作空闲30秒
    private final static int writerIdleTimeSeconds = 40;//写操作空闲60秒
    private final static int allIdleTimeSeconds = 100;//读写全部空闲100秒

    protected void initChannel(Channel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        pipeline.addLast(new ProtobufVarint32FrameDecoder());
        pipeline.addLast(new ProtobufDecoder(MessageBase.Message.getDefaultInstance()));
        pipeline.addLast(new ProtobufVarint32LengthFieldPrepender());
        pipeline.addLast(new ProtobufEncoder());
        pipeline.addLast("idleStateHandler", new IdleStateHandler(readerIdleTimeSeconds, writerIdleTimeSeconds,allIdleTimeSeconds));
        pipeline.addLast(new ProtobufClientHandler());
    }
}