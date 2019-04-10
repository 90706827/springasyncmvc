package com.example.springasyncmvc.controller;

import com.example.springasyncmvc.module.User;
import com.example.springasyncmvc.service.TaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.context.request.async.WebAsyncTask;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeoutException;

/**
 * @ClassName AsyncController
 * @Description
 * @Author Mr.Jangni
 * @Date 2019/4/10 21:05
 * @Version 1.0
 **/
@RestController
public class AsyncController {

    Logger logger = LoggerFactory.getLogger(AsyncController.class);

    @Autowired
    TaskService taskService;

    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public Object getUser() {
        logger.info(Thread.currentThread().getName() + " user");
        return taskService.getUser();
    }
    @RequestMapping(value = "/asyncuser", method = RequestMethod.GET)
    public Callable<User> getCallable() {
        logger.info(Thread.currentThread().getName() + " user");
        Callable<User> callable = () -> {
            logger.info(Thread.currentThread().getName() + " 进入user方法");
            User user = taskService.getUser();
            logger.info(Thread.currentThread().getName() + " 从taskService方法返回");
            return user;
        };
        logger.info(Thread.currentThread().getName() + " 从user方法返回");
        return callable;
    }

    @RequestMapping(value = "/deferred", method = RequestMethod.GET)
    public DeferredResult<User> getDeferred() {

        logger.info(Thread.currentThread().getName() + "进入 getDeferred 方法");

        DeferredResult<User> deferredResult = new DeferredResult<>();

        taskService.getDeferredUser(deferredResult);

        deferredResult.onCompletion(() -> {
            logger.info(Thread.currentThread().getName() + " onCompletion");
        });

        deferredResult.onTimeout(() -> {
            logger.info(Thread.currentThread().getName() + " onTimeout");
            // 返回超时信息
            deferredResult.setErrorResult("time out!");
        });

        return deferredResult;
    }

    @RequestMapping(value = "/webAsyncTask", method = RequestMethod.GET)
    public WebAsyncTask<User> getWebAsyncTask() {
        logger.info(Thread.currentThread().getName() + " user");

        WebAsyncTask<User> webAsyncTask = new WebAsyncTask<User>(3000L, "springAsyncExecutor", () -> {
            logger.info(Thread.currentThread().getName() + " 进入call方法");
            User user = taskService.getUser();
            logger.info(Thread.currentThread().getName() + " 从taskService方法返回");
            return user;
        });

        logger.info(Thread.currentThread().getName() + " 从user方法返回");

        webAsyncTask.onCompletion(() -> {
            logger.info(Thread.currentThread().getName() + " 执行完毕");
        });

        webAsyncTask.onTimeout(() -> {
            logger.info(Thread.currentThread().getName() + " onTimeout");
            // 超时的时候，直接抛异常，让外层统一处理超时异常
            throw new TimeoutException("调用超时");
        });

        return webAsyncTask;
    }


}