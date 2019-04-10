package com.example.springasyncmvc.async;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.Future;

import static java.lang.System.currentTimeMillis;
import static java.lang.System.out;
import static java.lang.Thread.sleep;

/**
 * @ClassName AsyncExecutorTaskTest
 * @Description
 * @Author Mr.Jangni
 * @Date 2019/4/10 20:20
 * @Version 1.0
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
public class AsyncExecutorTaskTest {
    @Autowired
    private AsyncExecutorTask task;

    @Test
    public void testAsyncCallbackTask() throws Exception {
        long start = currentTimeMillis();
        Future<String> task1 = task.doTaskOne();
        Future<String> task2 = task.doTaskTwo();
        Future<String> task3 = task.doTaskThree();

        // 三个任务都调用完成，退出循环等待
        while (!task1.isDone() || !task2.isDone() || !task3.isDone()) {

        }

        long end = currentTimeMillis();
        out.println("任务全部完成，总耗时：" + (end - start) + "毫秒");
    }

}