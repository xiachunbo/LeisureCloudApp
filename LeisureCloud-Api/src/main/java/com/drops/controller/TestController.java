package com.drops.controller;

import com.drops.lock.DataBaseLockUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


/**
 * @DESCRIPTION 测试类
 * @Author lst
 * @Date 2020-05-24
 */
@RestController
@RequestMapping("/testxxxxx")
public class TestController {

    public Logger logger = Logger.getLogger(TestController.class);

    @Autowired
    private DataBaseLockUtil dataBaseLockUtil;

    public final static String LOCK_KEY = "lock-key";

    public final static int DEFAULT_TIME_OUT = 60 * 10;

    /**
     * 通过数据库分布式锁高并发测试
     * @author lst
     * @date 2020-5-24 17:32
     * @param
     * @return com.example.mybatiesplus.result.BaseResponse
     */
    @GetMapping(value = "/dataBaseLock", produces = "application/json; charset=utf-8")
    @ResponseBody
    public String dataBaseLock() {
        try{
            logger.info("============={"+Thread.currentThread().getName()+"} 线程访问开始============");
            //TODO 获取分布式锁
            boolean lock = dataBaseLockUtil.tryLock(LOCK_KEY,DEFAULT_TIME_OUT);
            if (lock) {
                logger.info("线程:{"+Thread.currentThread().getName()+"}，获取到了锁");
                //TODO 获得锁之后可以进行相应的处理  睡一会
                Thread.sleep(100);
                logger.info("===={"+ Thread.currentThread().getName()+"}==获得锁后进行相应的操作======");
                dataBaseLockUtil.delLock(LOCK_KEY);
                logger.info("============================={"+Thread.currentThread().getName()+"} 释放了锁");
            }
        }catch (Exception e){
            logger.info("错误信息：{"+e.toString()+"}");
            logger.info("线程：{"+Thread.currentThread().getName()+"} 获取锁失败");
        }
        return "ok";
    }
}