package com.example.error.even;

import org.springframework.context.ApplicationEvent;

/**
 * @author: ljj
 * @date: 2022/6/30
 * @description:
 */
public class MyEvent extends ApplicationEvent {

    public MyEvent(Object source) {
        super(source);
    }
}
