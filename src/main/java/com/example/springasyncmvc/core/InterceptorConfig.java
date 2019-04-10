package com.example.springasyncmvc.core;

import com.example.springasyncmvc.interceptors.SpringAsyncHandlerInterceptor;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @ClassName InterceptorConfig
 * @Description 拦截器配置
 * @Author Mr.Jangni
 * @Date 2019/4/10 23:37
 * @Version 1.0
 **/
@Configuration
public class InterceptorConfig extends WebMvcConfigurerAdapter {

    @Autowired
    SpringAsyncHandlerInterceptor springAsyncHandlerInterceptor;


    public void addInterceptors(InterceptorRegistry registry){
        registry.addInterceptor(springAsyncHandlerInterceptor).addPathPatterns("/**");
        super.addInterceptors(registry);
    }
}