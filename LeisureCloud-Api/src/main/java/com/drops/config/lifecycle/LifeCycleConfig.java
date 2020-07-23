package com.drops.config.lifecycle;

import com.drops.config.lifecycle.SpringLifeCycle;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LifeCycleConfig {
    @Bean(initMethod = "start", destroyMethod = "destroy")
    public SpringLifeCycle create() {
        SpringLifeCycle springLifeCycle = new SpringLifeCycle();
        return springLifeCycle;
    }
}