package com.drops.config.lifecycle;

import com.drops.config.lifecycle.AnnotationBean;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

@Component
public class AnnotationBean {
    private static final Logger LOGGER = Logger.getLogger(AnnotationBean.class);

    @PostConstruct
    public void start() {
    }

    @PreDestroy
    public void destroy() {
    }
}