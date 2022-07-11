package com.jj.easy;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.jj.common.Driver;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

/**
 * @ClassName: EasyConsumer.java
 * @Author: jjl
 * @Description:
 * @Date: 2021-09-25
 * @Version: 1.0
 */

@Component
public class EasyConsumer {

//    @JmsListener(destination = "active_easy_queue")
//    public void aa(String aa) {
//        System.out.println("收到简单的消息" + JSON.parseObject(aa, Driver.class));
//    }

    @JmsListener(destination = "active_easy_queue", containerFactory = "jmsQueueListener")
    public void exportInfoPdfFromMq(final TextMessage text, Session session, Message jmsMessage) throws Exception {
//        System.out.println(text.getText());
        System.out.println("收到简单的消息" + text.getText());
        jmsMessage.acknowledge();
    }
}
