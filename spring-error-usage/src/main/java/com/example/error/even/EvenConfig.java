package com.example.error.even;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ApplicationEventMulticaster;
import org.springframework.context.event.SimpleApplicationEventMulticaster;

/**
 * @author: ljj
 * @date: 2022/6/30
 * @description: 监听出现异常可以继续处理
 */
@Configuration
public class EvenConfig {
    @Bean
    public ApplicationEventMulticaster applicationEventMulticaster(){
        SimpleApplicationEventMulticaster simpleApplicationEventMulticaster=new SimpleApplicationEventMulticaster();
        simpleApplicationEventMulticaster.setErrorHandler(LoggingErrorHandler.LOG_AND_SUPPRESS_ERROR_HANDLER);
        return  simpleApplicationEventMulticaster;
    }
}
