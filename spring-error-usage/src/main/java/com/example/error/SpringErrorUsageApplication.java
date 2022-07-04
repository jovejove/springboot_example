package com.example.error;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @author Administrator
 */
@SpringBootApplication
@EnableAspectJAutoProxy
@ComponentScans(value = {@ComponentScan("com.example.controller"), @ComponentScan("com.example.error.controller")})
@ServletComponentScan
@Slf4j
public class SpringErrorUsageApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(SpringErrorUsageApplication.class, args);
        log.info("启动成功");
//        context.close();

        // 监听出现异常可以继续处理
//        SimpleApplicationEventMulticaster simpleApplicationEventMulticaster = context.getBean(SimpleApplicationEventMulticaster.class);
//        simpleApplicationEventMulticaster.setErrorHandler(TaskUtils.LOG_AND_SUPPRESS_ERROR_HANDLER);
    }

}
