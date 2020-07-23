package com.drops.config.task;

import com.drops.common.spring.SpringUtil;
import com.drops.config.task.SchedulingRunnable;

import java.lang.reflect.Method;
import java.util.Objects;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.util.ReflectionUtils;

public class SchedulingRunnable
        implements Runnable {
    private static final Logger logger = Logger.getLogger(SchedulingRunnable.class);

    private String beanName;

    private String methodName;

    private String params;


    public SchedulingRunnable(String beanName, String methodName) {
        this(beanName, methodName, null);
    }


    public SchedulingRunnable(String beanName, String methodName, String params) {
        this.beanName = beanName;
        this.methodName = methodName;
        this.params = params;
    }


    public void run() {
        logger.info("定时任务开始执行 - bean：{" + this.beanName + "}，方法：{" + this.methodName + "}，参数：{" + this.params + "}");
        long startTime = System.currentTimeMillis();

        try {
            Object target = SpringUtil.getBean(this.beanName);

            Method method = null;
            if (StringUtils.isNotEmpty(this.params)) {
                method = target.getClass().getDeclaredMethod(this.methodName, new Class[]{String.class});
            } else {
                method = target.getClass().getDeclaredMethod(this.methodName, new Class[0]);
            }

            ReflectionUtils.makeAccessible(method);
            if (StringUtils.isNotEmpty(this.params)) {
                method.invoke(target, new Object[]{this.params});
            } else {
                method.invoke(target, new Object[0]);
            }
        } catch (Exception ex) {
            logger.error(String.format("定时任务执行异常 - bean：%s，方法：%s，参数：%s ", new Object[]{this.beanName, this.methodName, this.params}), ex);
        }

        long times = System.currentTimeMillis() - startTime;
        logger.info("定时任务执行结束 - bean：{" + this.beanName + "}，方法：{" + this.methodName + "}，参数：{" + this.params + "}，耗时：{" + times + "} 毫秒");
    }


    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SchedulingRunnable that = (SchedulingRunnable) o;
        if (this.params == null) {
            return (this.beanName.equals(that.beanName) && this.methodName
                    .equals(that.methodName) && that.params == null);
        }


        return (this.beanName.equals(that.beanName) && this.methodName
                .equals(that.methodName) && this.params
                .equals(that.params));
    }


    public int hashCode() {
        if (this.params == null) {
            return Objects.hash(new Object[]{this.beanName, this.methodName});
        }
        return Objects.hash(new Object[]{this.beanName, this.methodName, this.params});
    }
}