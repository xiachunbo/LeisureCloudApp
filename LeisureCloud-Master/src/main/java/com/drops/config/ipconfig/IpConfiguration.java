package com.drops.config.ipconfig;

import org.springframework.boot.web.context.WebServerInitializedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class IpConfiguration
        implements ApplicationListener<WebServerInitializedEvent> {
    private int serverPort;

    public void onApplicationEvent(WebServerInitializedEvent event) { this.serverPort = event.getWebServer().getPort(); }

    public int getPort() { return this.serverPort; }
}
