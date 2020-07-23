package com.drops.config.task;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

import java.util.concurrent.ThreadPoolExecutor;

@Configuration
public class SchedulingConfig {
    @Bean("taskExecutor")
    @Primary
    public TaskScheduler taskScheduler() {
        ThreadPoolTaskScheduler taskScheduler = new ThreadPoolTaskScheduler();
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(10);//核心线程数目
        executor.setMaxPoolSize(20);//指定最大线程数
        executor.setQueueCapacity(200);//队列中最大的数目
        executor.setKeepAliveSeconds(60);//线程空闲后的最大存活时间
        executor.setThreadNamePrefix("LeisureCloud-Api-taskExecutor-");//线程名称前缀
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        executor.initialize();
        return (TaskScheduler) taskScheduler;
    }
}