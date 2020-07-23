package com.drops.service.impl;

import com.drops.service.AsyncThreadService;
import org.apache.log4j.Logger;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.Future;
@Service
public class AsyncThreadServiceImpl implements AsyncThreadService {
    Logger logger  =  Logger.getLogger(AsyncThreadServiceImpl.class );
    @Async("asyncServiceExecutor")
    @Override
    public Future<String> doReturn(int i) {
        logger.info(">>>>>>>>>>>>>>线程名>>>>>>>>>>>>>>" + Thread.currentThread().getName());
        try {
            // 这个方法需要调用500毫秒
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        // 消息汇总
        logger.info(">>>>>>>>>>>>>>线程名>>>>>>>>>>>>>>" + Thread.currentThread().getName()+String.format("这个是第{%s}个异步调用的证书", i));
        return new AsyncResult<>(String.format("这个是第{%s}个异步调用的证书", i));
    }
}
