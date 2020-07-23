package com.drops.nettyclient;

import com.drops.protoco.MessageBase;
import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.CharsetUtil;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;

import java.net.InetSocketAddress;
import java.util.HashMap;
import java.util.Map;

/**
 * netty客户端 . <br>
 *
 * @author hkb
 */
public class NettyClient {
    Logger logger  =  Logger.getLogger(NettyClient.class );
    /**
     * 主机
     */
    public static String host;

    /**
     * 端口号
     */
    public static int port;
    private static Bootstrap bootstrap;
    private static ChannelFutureListener channelFutureListener = null;
    private static  ByteBuf HEARTBEAT_SEQUENCE = Unpooled.unreleasableBuffer(Unpooled.copiedBuffer(Unpooled.copiedBuffer("connect",
            CharsetUtil.UTF_8)));
    /**
     * 构造函数
     *
     * @param host
     * @param port
     */
    public NettyClient(String host, int port) {
        this.host = host;
        this.port = port;
    }

    /**
     * 连接方法
     */
    public void connect() throws InterruptedException {
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(group).channel(NioSocketChannel.class);
            bootstrap.option(ChannelOption.TCP_NODELAY, true);
            bootstrap.option(ChannelOption.AUTO_READ, true);
            //bootstrap.handler(new NettyClientInitializer());
            bootstrap.handler(new HeartbeatHandlerClientInitializer());
            ChannelFuture future = bootstrap.connect(new InetSocketAddress(
                    host, port));
            future.addListener(new ConnectionListener());
            Channel channel = future.sync().channel();
            // 发送字符串
            Map<String,String> params=new HashMap<String,String>();
            params.put("user","LeisureCloud-Proxy");
            params.put("data","connect");
            JSONObject jsonObject= JSONObject.fromObject(params);
            String jsonStr=jsonObject.toString();
            MessageBase.Message.Builder message = new MessageBase.Message.Builder();
            message.setRequestId("1");
            message.setContent(jsonStr);
            //HEARTBEAT_SEQUENCE = Unpooled.unreleasableBuffer(Unpooled.copiedBuffer(Unpooled.copiedBuffer(jsonStr,
            //        CharsetUtil.UTF_8)));
            //channel.writeAndFlush(HEARTBEAT_SEQUENCE.duplicate());
            channel.writeAndFlush(message.build());
            channel.closeFuture().sync();
        } catch (Exception e) {
            //e.printStackTrace();
        } finally {
            Thread.sleep(3500);
            group.shutdownGracefully();
        }
    }
}
