package com.jj.topic;

import com.jj.common.Driver;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
 * @ClassName: ActiveTopicConsumer.java
 * @Author: jjl
 * @Description:
 * @Date: 2021-09-25
 * @Version: 1.0
 */
@Component
public class ActiveTopicConsumer {
    /**
     * destination 为定义的topic的名字
     * concurrency 消费者并发数
     * containerFactory 之前设置的JmsListenerContainerFactory bean 名字
     */
    @JmsListener(destination = "jj_topic", concurrency = "1", containerFactory = "jjTopicFactory")
    public void ListenTopic(Driver driver) {
        System.out.println(Thread.currentThread().getName() + "消费者1接收到topic消息：" + driver);
    }

    @JmsListener(destination = "jj_topic", concurrency = "10", containerFactory = "jjTopicFactory")
    public void ListenTopic2(Driver driver) {
        System.out.println(Thread.currentThread().getName() + "--消费者--2--接收到topic消息：" + driver);
    }
}
