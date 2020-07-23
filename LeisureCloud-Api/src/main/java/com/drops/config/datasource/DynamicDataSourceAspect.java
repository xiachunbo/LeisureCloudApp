package com.drops.config.datasource;

import com.drops.config.datasource.DataSourceContextHolder;
import com.drops.config.datasource.SwitchDataSource;

import java.lang.reflect.Method;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;


@Aspect
@Component
public class DynamicDataSourceAspect {
    @Before("@annotation(com.drops.config.datasource.SwitchDataSource)")
    public void beforeSwitchDS(JoinPoint point) {
        Class<?> className = point.getTarget().getClass();

        String methodName = point.getSignature().getName();

        Class[] argClass = ((MethodSignature) point.getSignature()).getParameterTypes();
        String dataSource = "datasource1";

        try {
            Method method = className.getMethod(methodName, argClass);

            if (method.isAnnotationPresent((Class) SwitchDataSource.class)) {
                SwitchDataSource annotation = method.getAnnotation(SwitchDataSource.class);

                dataSource = annotation.value();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        DataSourceContextHolder.setDB(dataSource);
    }


    @After("@annotation(com.drops.config.datasource.SwitchDataSource)")
    public void afterSwitchDS(JoinPoint point) {
        DataSourceContextHolder.clearDB();
    }
}