package com.drops.tools;

import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * 获取spring中的bean对象工具类
 * @date 2018-07-23 17:42
 */
@Component
public class SpringContextUtil  implements ApplicationContextAware {

    /**
     *  Spring应用上下文环境
     */
    private static ApplicationContext applicationContext;


    /**
     * 实现ApplicationContextAware接口的回调方法，设置上下文环境
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext)
            throws BeansException {
        SpringContextUtil.applicationContext = applicationContext;
    }

    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    /**
     * 获取对象 这里重写了bean方法，起主要作用
     */
    public static Object getBean(String beanId) throws BeansException {
        return applicationContext.getBean(beanId);
    }
    public static <T> T getBean(Class<T> clazz) throws BeansException {
        return (T) applicationContext.getBean(clazz);
    }
}