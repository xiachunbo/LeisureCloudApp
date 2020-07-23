package com.drops.service.impl;

import com.drops.service.impl.SpringLifeCycleService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;

@Service
public class SpringLifeCycleService implements InitializingBean, DisposableBean {
    private static final Logger LOGGER = Logger.getLogger(SpringLifeCycleService.class);

    public void afterPropertiesSet() throws Exception {
    }

    public void destroy() throws Exception {
    }
}