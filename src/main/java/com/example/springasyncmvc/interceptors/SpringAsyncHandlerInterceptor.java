package com.example.springasyncmvc.interceptors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.AsyncHandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName SpringAsyncHandlerInterceptor
 * @Description 当Controller中有异步请求的时候会触发该拦截器
 * @Author Mr.Jangni
 * @Date 2019/4/10 23:26
 * @Version 1.0
 **/
@Component
public class SpringAsyncHandlerInterceptor implements AsyncHandlerInterceptor {

    Logger logger = LoggerFactory.getLogger(SpringAsyncHandlerInterceptor.class);

    @Override
    public void afterConcurrentHandlingStarted(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        logger.info("AsyncHandlerInterceptor 异步拦截器特有方法 只有异步在处理此方法");
        logger.info("afterConcurrentHandlingStarted");
    }

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        logger.info("拦截器 preHandle");
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        logger.info("拦截器 postHandle");
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        logger.info("拦截器 afterCompletion");
    }
}