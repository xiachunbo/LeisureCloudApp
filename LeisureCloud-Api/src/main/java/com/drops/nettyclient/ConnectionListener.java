package com.drops.nettyclient;


import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.EventLoop;
import org.apache.log4j.Logger;

import java.util.concurrent.TimeUnit;

class ConnectionListener implements ChannelFutureListener {
    Logger logger  =  Logger.getLogger(NettyClient.class );
    @Override
    public void operationComplete(ChannelFuture channelFuture) throws Exception {
        if (!channelFuture.isSuccess()) {
            final EventLoop loop = channelFuture.channel().eventLoop();
            loop.schedule(new Runnable() {
                @Override
                public void run() {
                    logger.error("服务端链接不上，开始重连操作...");
                    try {
                        NettyClient nettyClient = new NettyClient(NettyClient.host,NettyClient.port);
                        nettyClient.connect();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }, 3, TimeUnit.SECONDS);
        } else {
            logger.info("服务端链接成功...");
        }
    }
}
