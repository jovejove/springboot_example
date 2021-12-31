package com.panda.core.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;

/**
 * 监听配置类
 * 
 * @author oKong
 *
 */
@Configuration
@Slf4j
public class EventListenerConfig {

    @EventListener
    public void handleEvent(Object event) {
        //监听所有事件 可以看看 系统各类时间 发布了哪些事件
        //可根据 instanceof 监听想要监听的事件
//        if(event instanceof CustomEvent) {
//            
//        }
//        log.info("事件：{}", event);
    }

    /**
     * 异步指定对象监听 不阻塞后续操作
     * @param customEvent
     * @throws InterruptedException
     *
     * @TransactionalEventListener注解可解决异步事务问题
     */
    @Async
    @EventListener
    public void handleCustomEvent(CustomEvent customEvent) throws InterruptedException {
        //监听 CustomEvent事件
        Thread.sleep(2000);
        log.info("监听到CustomEvent事件，消息为：{}, 发布时间：{}", customEvent.getMessageEntity(), customEvent.getTimestamp());
    }
    
    /**
     * 监听 code为panda的事件
     */
    @EventListener(condition="#customEvent.messageEntity.code == 'panda'")
    public void handleCustomEventByCondition(CustomEvent customEvent) {
        //监听 CustomEvent事件
//        log.info("监听到code为'oKong'的CustomEvent事件，消息为：{}, 发布时间：{}", customEvent.getMessageEntity(), customEvent.getTimestamp());
    }

    /**
     * 指定对象监听
     * @param messageEntity
     */
    @EventListener 
    public void handleObjectEvent(MessageEntity messageEntity) {
        //这个和eventbus post方法一样了
        log.info("1-监听到对象事件，消息为：{}", messageEntity);
        
    }
}