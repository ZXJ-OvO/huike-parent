package com.huike.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@Configuration
public class ThreadPoolConfig {

    /**
     * 自定义线程池
     *
     * @return
     */
    @Bean
    public Executor executor() {
        //创建线程池

        //简单：
        //ExecutorService threadPool = Executors.newFixedThreadPool(10);

        //复杂：
        /*ThreadPoolExecutor executor = new ThreadPoolExecutor(10,
                20,
                100,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(10),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy());*/

        //spring:
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(10);
        executor.setMaxPoolSize(20);
        executor.setThreadNamePrefix("huike-pool-");

        return executor;
    }


}
