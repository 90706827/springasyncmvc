package com.example.springasyncmvc.core;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * @ClassName AsyncConfig
 * @Description 线程池配置
 * @Author Mr.Jangni
 * @Date 2019/4/10 20:59
 * @Version 1.0
 **/

@Configuration
@EnableAsync
public class AsyncConfig {
    @Bean
    public Executor springAsyncExecutor() {

        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();

        //核心线程数-线程池创建时候初始化的线程数
        executor.setCorePoolSize(10);

        //最大线程数-线程池最大的线程数，只有在缓冲队列满了之后，才会申请超过核心线程数的线程
        executor.setMaxPoolSize(50);

        //缓冲队列-用来缓冲执行任务的队列
        executor.setQueueCapacity(200);

        //该方法用来设置 线程池关闭的时候 等待所有任务都完成后，再继续销毁其他的Bean，
        // 这样这些异步任务的销毁就会先于数据库连接池对象 的销毁。
        executor.setWaitForTasksToCompleteOnShutdown(true);

        //该方法用来设置线程池中 任务的等待时间，如果超过这个时间还没有销毁就 强制销毁，
        // 以确保应用最后能够被关闭，而不是阻塞住。
        executor.setAwaitTerminationSeconds(60);

        //线程池名的前缀-可以用于定位处理任务所在的线程池
        executor.setThreadNamePrefix("spring-async-exec-");
        //线程池对拒绝任务的处理策略-这里采用CallerRunsPolicy策略，当线程池没有处理能力的时候，
        // 该策略会直接在execute方法的调用线程中运行被拒绝的任务；如果执行程序已关闭，则会丢弃该任务
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());

        executor.initialize();
        return executor;
    }
}