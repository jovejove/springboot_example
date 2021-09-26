package com.jj.virtual;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;

/**
 * @ClassName: VirtualConsumer.java
 * @Author: jjl
 * @Description:
 * @Date: 2021-09-26
 * @Version: 1.0
 */

@Component
@Slf4j
public class VirtualConsumer {

    /**
     * 设置Consumer.A. 两个
     *
     * @param receiveStr
     * @throws JMSException
     */
    @JmsListener(destination = "Consumer.A.VirtualTopic.Orders", containerFactory = "virtualFactory")
    public void receiveTopicQueueA1(String receiveStr) throws JMSException {
        log.info("Consumer.A1-----Orders:{}", receiveStr);

    }

    /**
     * 设置Consumer.A. 两个
     *
     * @param receiveStr
     * @throws JMSException
     */
    @JmsListener(destination = "Consumer.A.VirtualTopic.Orders", concurrency = "50", containerFactory = "virtualFactory")
    public void receiveTopicQueueA2(String receiveStr) throws JMSException {
        log.info("Consumer.A2-----Orders:{}", receiveStr);

    }

    /**
     * 设置Consumer.B. 1个
     *
     * @param receiveStr
     * @throws JMSException
     */
    @JmsListener(destination = "Consumer.B.VirtualTopic.Orders", containerFactory = "virtualFactory")
    public void receiveTopicQueueB(String receiveStr) throws JMSException {
        log.info("Consumer.B-----Orders:{}", receiveStr);

    }
}
