package com.drops.common.ratelimit;

import com.drops.common.ratelimit.RateLimit;
import com.drops.common.ratelimit.RateLimitAspect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.util.concurrent.RateLimiter;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.concurrent.ConcurrentHashMap;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;


@Component
@Scope
@Aspect
public class RateLimitAspect {
    private Logger log = LoggerFactory.getLogger(getClass());

    private ConcurrentHashMap<String, RateLimiter> map = new ConcurrentHashMap<>();

    private static ObjectMapper objectMapper = new ObjectMapper();

    private RateLimiter rateLimiter;

    @Autowired
    private HttpServletResponse response;


    @Pointcut("@annotation(com.drops.common.ratelimit.RateLimit)")
    public void serviceLimit() {
    }

    @Around("serviceLimit()")
    public Object around(ProceedingJoinPoint joinPoint) throws NoSuchMethodException {
        Object obj = null;

        Signature sig = joinPoint.getSignature();

        MethodSignature msig = (MethodSignature) sig;

        Object target = joinPoint.getTarget();

        Method currentMethod = target.getClass().getMethod(msig.getName(), msig.getParameterTypes());

        RateLimit annotation = currentMethod.getAnnotation(RateLimit.class);
        double limitNum = annotation.limitNum();
        String functionName = msig.getName();


        if (this.map.containsKey(functionName)) {
            this.rateLimiter = this.map.get(functionName);
        } else {
            this.map.put(functionName, RateLimiter.create(limitNum));
            this.rateLimiter = this.map.get(functionName);
        }

        try {
            if (this.rateLimiter.tryAcquire()) {
                obj = joinPoint.proceed();
            } else {
                this.log.info("拒绝了请求");
            }

        } catch (Throwable throwable) {
        }


        return obj;
    }

    public void outErrorResult(String result) {
        this.response.setContentType("application/json;charset=UTF-8");
        try (ServletOutputStream outputStream = this.response.getOutputStream()) {
            outputStream.write(result.getBytes("utf-8"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static {
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    }
}