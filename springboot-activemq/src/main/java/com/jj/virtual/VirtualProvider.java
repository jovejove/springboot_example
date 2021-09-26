package com.jj.virtual;

import lombok.extern.slf4j.Slf4j;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Component;

/**
 * @ClassName: VirtualProvider.java
 * @Author: jjl
 * @Description:
 * @Date: 2021-09-26
 * @Version: 1.0
 */
@Component
@Slf4j
public class VirtualProvider {
    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;
    @Autowired
    private ActiveMQTopic virtualTopicQueue;

    public void sendTopicMessage(String message) {
        for (int i = 0; i < 5; i++) {
            jmsMessagingTemplate.convertAndSend(virtualTopicQueue, message);
        }
    }
}
