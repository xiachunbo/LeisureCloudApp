package com.drops.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import java.net.InetSocketAddress;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class TCPServer
{
    @Autowired
    @Qualifier("serverBootstrap")
    private ServerBootstrap b;
    @Autowired
    @Qualifier("tcpSocketAddress")
    private InetSocketAddress tcpPort;
    private ChannelFuture serverChannelFuture;

    @PostConstruct
    public void start() throws Exception {
        System.out.println("Starting server at " + this.tcpPort);
        this.serverChannelFuture = this.b.bind(this.tcpPort).sync();
    }


    @PreDestroy
    public void stop() throws Exception { this.serverChannelFuture.channel().closeFuture().sync(); }



    public ServerBootstrap getB() { return this.b; }



    public void setB(ServerBootstrap b) { this.b = b; }



    public InetSocketAddress getTcpPort() { return this.tcpPort; }



    public void setTcpPort(InetSocketAddress tcpPort) { this.tcpPort = tcpPort; }
}
