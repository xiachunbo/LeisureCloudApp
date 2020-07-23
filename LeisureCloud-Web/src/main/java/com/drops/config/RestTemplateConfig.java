package com.drops.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * Created by xiaobobo on 22/8/18.
 */
@Configuration
public class RestTemplateConfig {

    @Bean
    public RestTemplate cteateRestTemplate(){
        return new RestTemplate();
    }

}