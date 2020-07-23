package com.drops.netty;

import com.drops.protoco.MessageBase;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.timeout.IdleStateHandler;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;


@Component
@Qualifier("springProtocolInitializer")
public class StringProtocolInitalizer
        extends ChannelInitializer<SocketChannel> {
    private final static int readerIdleTimeSeconds = 40;//读操作空闲30秒
    private final static int writerIdleTimeSeconds = 50;//写操作空闲60秒
    private final static int allIdleTimeSeconds = 100;//读写全部空闲100秒
    @Autowired
    StringDecoder stringDecoder;
    @Autowired
    StringEncoder stringEncoder;
    protected void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        pipeline.addLast(new ProtobufVarint32FrameDecoder());
        pipeline.addLast(new ProtobufDecoder(MessageBase.Message.getDefaultInstance()));
        pipeline.addLast(new ProtobufVarint32LengthFieldPrepender());
        pipeline.addLast(new ProtobufEncoder());
        pipeline.addLast("idleStateHandler", new IdleStateHandler(readerIdleTimeSeconds, writerIdleTimeSeconds,allIdleTimeSeconds));
        pipeline.addLast(new ProtobufServerHandler());
    }
}
