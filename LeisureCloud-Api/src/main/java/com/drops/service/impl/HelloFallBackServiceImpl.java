package com.drops.service.impl;

import com.drops.service.HelloService;
import org.springframework.stereotype.Service;

@Service("helloFallbackService")
public class HelloFallBackServiceImpl implements HelloService {

    @Override
    public String hello(String userName) {

        return "fallBack:"+userName;
    }
}