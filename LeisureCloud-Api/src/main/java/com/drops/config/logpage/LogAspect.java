package com.drops.config.logpage;

import com.alibaba.fastjson.JSON;
import com.drops.config.logpage.LogAspect;
import com.drops.config.logpage.OperationLog;
import com.drops.config.logpage.OperationLogDetail;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import com.drops.service.GenericService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Aspect
@Component
public class LogAspect {
    @Autowired
    private GenericService genericService;
    @Pointcut("@annotation(com.drops.config.logpage.OperationLogDetail)")
    public void operationLog() {
    }

    @Around("operationLog()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        Object res = null;
        long time = System.currentTimeMillis();
        try {
            res = joinPoint.proceed();
            time = System.currentTimeMillis() - time;
            return res;
        } finally {

            try {
                addOperationLog((JoinPoint) joinPoint, res, time);
            } catch (Exception e) {
                System.out.println("LogAspect 操作失败：" + e.getMessage());
                e.printStackTrace();
            }
        }
    }

    private void addOperationLog(JoinPoint joinPoint, Object res, long time) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        OperationLog operationLog = new OperationLog();
        operationLog.setRunTime(Long.valueOf(time));
        operationLog.setReturnValue(JSON.toJSONString(res));
        operationLog.setId(UUID.randomUUID().toString());
        operationLog.setArgs(JSON.toJSONString(joinPoint.getArgs()));
        operationLog.setCreateTime(new Date());
        operationLog.setMethod(signature.getDeclaringTypeName() + "." + signature.getName());
        operationLog.setUserId("admin");
        OperationLogDetail annotation = signature.getMethod().getAnnotation(OperationLogDetail.class);
        if (annotation != null) {
            operationLog.setLevel(Integer.valueOf(annotation.level()));
            operationLog.setDescribe(getDetail(((MethodSignature) joinPoint.getSignature()).getParameterNames(), joinPoint.getArgs(), annotation));
            operationLog.setOperationType(annotation.operationType().getValue());
            operationLog.setOperationUnit(annotation.operationUnit().getValue());
        }
        System.out.println("记录日志:" + operationLog.toString());
    }


    private String getDetail(String[] argNames, Object[] args, OperationLogDetail annotation) {
        Map<Object, Object> map = new HashMap<>(4);
        for (int i = 0; i < argNames.length; i++) {
            map.put(argNames[i], args[i]);
        }

        String detail = annotation.detail();
        try {
            detail = "'#{currentUserName}'=》" + annotation.detail();
            for (Map.Entry<Object, Object> entry : map.entrySet()) {
                Object k = entry.getKey();
                Object v = entry.getValue();
                detail = detail.replace("{{" + k + "}}", JSON.toJSONString(v));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return detail;
    }


    @Before("operationLog()")
    public void doBeforeAdvice(JoinPoint joinPoint) {
        System.out.println("方法执行开始.....");
    }


    @AfterReturning(returning = "ret", pointcut = "operationLog()")
    public void doAfterReturning(Object ret) {
    }


    @AfterThrowing("operationLog()")
    public void throwss(JoinPoint jp) {
        System.out.println("方法异常时执行.....");
    }


    @After("operationLog()")
    public void after(JoinPoint jp) {
        System.out.println("方法执行完毕.....");
    }
}