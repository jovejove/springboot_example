package com.example.error.even;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationEnvironmentPreparedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * spring没有发布这个事件ApplicationEnvironmentPreparedEvent
 * 解决办法：
 * 1. 在上下文中添加该事件
 * 2.  在resources/META-INF/spring.factories配置监听器
 */
@Slf4j
@Component
public class MyApplicationEnvironmentPreparedEventListener implements ApplicationListener<ApplicationEnvironmentPreparedEvent> {

    @Override
    public void onApplicationEvent(final ApplicationEnvironmentPreparedEvent event) {
        log.info("{} received: {}", this.toString(), event);
    }

}