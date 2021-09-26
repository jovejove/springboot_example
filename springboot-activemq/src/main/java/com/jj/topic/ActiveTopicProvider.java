package com.jj.topic;

import com.jj.common.Driver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Service;

/**
 * @ClassName: ActiveTopicProvider.java
 * @Author: jjl
 * @Description:
 * @Date: 2021-09-26
 * @Version: 1.0
 */
@Service
public class ActiveTopicProvider {
    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    public void sendMessage() {
        for (int i = 0; i < 30; i++) {
            jmsMessagingTemplate.convertAndSend("jj_topic", new Driver((long) i, "司机" + i));
        }
    }
}
