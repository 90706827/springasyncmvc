package com.example.springasyncmvc.async;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

import java.util.Random;
import java.util.concurrent.Future;

import static java.lang.System.currentTimeMillis;
import static java.lang.System.out;
import static java.lang.Thread.sleep;

/**
 * @ClassName AsyncExecutorTask
 * @Description 异步任务
 * @Author Mr.Jangni
 * @Date 2019/4/10 20:16
 * @Version 1.0
 **/
@Component
public class AsyncExecutorTask {
    private static Random random = new Random();
    Logger logger = LoggerFactory.getLogger(AsyncExecutorTask.class);

    @Async("springAsyncExecutor")
    public Future<String> doTaskOne() throws Exception {
        out.println("开始做任务一");
        long start = currentTimeMillis();
        sleep(random.nextInt(10000));
        long end = currentTimeMillis();
        out.println("完成任务一，耗时：" + (end - start) + "毫秒");

        out.println("任务一，当前线程：" + Thread.currentThread().getName());
        return new AsyncResult<>("任务一完成");
    }

    @Async("springAsyncExecutor")
    public Future<String> doTaskThree() throws Exception {
        out.println("开始做任务三");
        long start = currentTimeMillis();
        sleep(random.nextInt(10000));
        long end = currentTimeMillis();
        out.println("完成任务三，耗时：" + (end - start) + "毫秒");
        out.println("任务三，当前线程：" + Thread.currentThread().getName());
        return new AsyncResult<>("任务二完成");
    }

    @Async("springAsyncExecutor")
    public Future<String> doTaskTwo() throws Exception {
        out.println("开始做任务二");
        long start = currentTimeMillis();
        sleep(random.nextInt(10000));
        long end = currentTimeMillis();
        out.println("完成任务二，耗时：" + (end - start) + "毫秒");
        out.println("任务二，当前线程：" + Thread.currentThread().getName());
        return new AsyncResult<>("任务三完成");
    }

}