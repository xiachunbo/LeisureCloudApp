package com.drops.common;

import com.drops.interceptor.AuthorityInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@EnableWebMvc
@Configuration
public class WebConfig
        implements WebMvcConfigurer {
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(new String[]{"/templates/**"}).addResourceLocations(new String[]{"classpath:/templates/"});
        registry.addResourceHandler(new String[]{"/static/**"}).addResourceLocations(new String[]{"classpath:/static/"});
    }


    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor((HandlerInterceptor) new AuthorityInterceptor())
                .addPathPatterns(new String[]{"/**"}).excludePathPatterns(new String[]{"/login", "/static/**"});
    }
}