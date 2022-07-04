package com.example.error.even;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
@Order(1)
@Slf4j
public class MyFirstEventListener implements ApplicationListener<MyEvent> {
    Random random = new Random();

    @Override
    public void onApplicationEvent(MyEvent event) {
        log.info("{} received: {}", this.toString(), event);
        //模拟部分失效
        if (random.nextInt(10) % 2 == 1) {
//            try {
            /**
             *  监听器抛出异常会导致胡须监听器无法执行监听
             *  最终事件的执行是由同一个线程按顺序来完成的，任何一个报错，都会导致后续的监听器执行不了。
             *  解决方法
             *  1：使用catch异常
             *  2：实现接口ErrorHandler
             */
            throw new RuntimeException("exception happen on first listener");
//            } catch (RuntimeException e) {
//                e.printStackTrace();
//            }
        }
    }
}
