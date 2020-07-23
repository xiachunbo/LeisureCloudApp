package com.drops.service.impl;

import com.drops.service.SysService;
import org.springframework.stereotype.Service;


@Service
public class SysServiceImpl
        implements SysService {
    public void job(String parem) {
        System.out.println(parem);
    }
}