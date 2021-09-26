package com.jj;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQQueue;

import javax.jms.*;

/**
 * @ClassName: QueueTest.java
 * @Author: jjl
 * @Description:
 * @Date: 2021-09-26
 * @Version: 1.0
 */
public class QueueTest {
    public static void main(String[] args) throws JMSException {
        ActiveMQConnectionFactory factory = new ActiveMQConnectionFactory("vm://localhost");

        Connection connection = factory.createConnection();

        connection.start();

        //创建一个Queue
        Queue queue = new ActiveMQQueue("testQueue");
        //创建一个Session
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);

        // consumer1
        MessageConsumer consumer1 = session.createConsumer(queue);
        consumer1.setMessageListener(new MessageListener() {
            @Override
            public void onMessage(Message message) {
                try {
                    System.out.println("Consumer1 get " + ((TextMessage) message).getText());
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        });

        // consumer2
        MessageConsumer consumer2 = session.createConsumer(queue);
        consumer2.setMessageListener(new MessageListener() {
            @Override
            public void onMessage(Message message) {
                try {
                    System.out.println("consumer2 get " + ((TextMessage) message).getText());
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        });

        //创建一个生产者，然后发送多个消息。
        MessageProducer producer = session.createProducer(queue);
        for (int i = 0; i < 10; i++) {
            producer.send(session.createTextMessage("Message:" + i));
        }

    }
}
