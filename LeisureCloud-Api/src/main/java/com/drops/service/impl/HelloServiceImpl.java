package com.drops.service.impl;

import com.drops.common.downgrade.ResouceDowngrade;
import com.drops.service.HelloService;
import org.springframework.stereotype.Service;

@Service("helloService")
public class HelloServiceImpl implements HelloService {

    @ResouceDowngrade(fallbackClass = HelloFallBackServiceImpl.class,resouceId = "hello",maxThreshold = 2)
    public String hello(String userName) {

        try {
            //模拟业务请求
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
        }
        return "hello:" + userName;
    }
}