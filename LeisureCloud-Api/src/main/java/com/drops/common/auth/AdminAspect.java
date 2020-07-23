package com.drops.common.auth;

import com.drops.common.auth.AdminAspect;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AdminAspect {
    @Pointcut("@annotation(com.drops.common.auth.Admin)")
    public void annotationPointCut() {
    }

    @Around("annotationPointCut()")
    public Object doAround(ProceedingJoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        String methodName = signature.getMethod().getName();
        System.out.println("方法名：" + methodName);
        if (!validate()) {
            return "没有权限";
        }
        try {
            return joinPoint.proceed();
        } catch (Throwable throwable) {
            return null;
        }
    }

    private boolean validate() {
        return true;
    }
}