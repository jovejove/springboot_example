package com.panda.core.listener;

import org.springframework.context.ApplicationEvent;

/**
 * 编写事件源
 *
 * @author Administrator
 */
public class CustomEvent extends ApplicationEvent {

    private final MessageEntity messageEntity;
    
    public CustomEvent(Object source, MessageEntity messageEntity) {
        super(source);
        this.messageEntity = messageEntity;
    }
    
    public MessageEntity getMessageEntity() {
        return this.messageEntity;
    }
}