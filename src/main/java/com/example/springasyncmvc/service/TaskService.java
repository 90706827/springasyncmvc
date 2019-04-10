package com.example.springasyncmvc.service;

import com.example.springasyncmvc.module.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.async.DeferredResult;

/**
 * @ClassName TaskService
 * @Description
 * @Author Mr.Jangni
 * @Date 2019/4/10 21:15
 * @Version 1.0
 **/
@Component
public class TaskService {

    Logger logger = LoggerFactory.getLogger(TaskService.class);

    @Async("springAsyncExecutor")
    public void getDeferredUser(DeferredResult<User> deferred) {
        logger.info(Thread.currentThread().getName() + " getSleepUser");

        User user = new User();
        user.setUsername("lisi");
        user.setPassword("12345678");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        deferred.setResult(user);
    }

    public User getUser() {
        logger.info(Thread.currentThread().getName() + " getUser");
        User user = new User();
        user.setUsername("zhangsan");
        user.setPassword("12345678");
        return user;
    }

}