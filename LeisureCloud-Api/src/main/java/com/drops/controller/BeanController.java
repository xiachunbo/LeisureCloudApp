package com.drops.controller;

import com.drops.common.bean.GenerateObjectUtil;
import com.drops.common.spring.SpringUtil;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
@RestController
public class BeanController {
    @RequestMapping({"/app/insertBean"})
    public void insertBean(String beanName){
        //获取全局上下文
        ApplicationContext ctx = SpringUtil.getApplicationContext();
        //获取BeanFactory
        DefaultListableBeanFactory defaultListableBeanFactory = (DefaultListableBeanFactory) ctx.getAutowireCapableBeanFactory();
        //创建bean信息.
        Object obj = null;
        Map<String, Object> map = new HashMap<>();
        map.put("field", "");
        try {
            //生成出bean的get set 函数
            obj = GenerateObjectUtil.generateObjectByField(map);
        } catch (Exception e) {
            obj = new Object();
        }
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(obj.getClass());
        beanDefinitionBuilder.addPropertyValue("field","张三");
        //动态注册bean.
        defaultListableBeanFactory.registerBeanDefinition(beanName, beanDefinitionBuilder.getBeanDefinition());
    }
    @RequestMapping({"/app/deleteBean"})
    public void deleteBean(String beanName){
        //获取全局上下文
        ApplicationContext ctx = SpringUtil.getApplicationContext();
        //获取BeanFactory
        DefaultListableBeanFactory defaultListableBeanFactory = (DefaultListableBeanFactory) ctx.getAutowireCapableBeanFactory();
        // 动态删除
        defaultListableBeanFactory.removeBeanDefinition(beanName);
    }
    @RequestMapping({"/app/findBean"})
    public Object findBean(String beanName){
        //获取全局上下文
        ApplicationContext ctx = SpringUtil.getApplicationContext();
        //获取动态注册的bean.
        return ctx.getBean(beanName);
    }
}
