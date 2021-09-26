package com.jj.easy;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * @ClassName: JmsConsumer.java
 * @Author: jjl
 * @Description:
 * @Date: 2021-09-26
 * @Version: 1.0
 */
public class JmsConsumer {

    private static final String USERNAME = ActiveMQConnection.DEFAULT_USER;//默认连接用户名
    private static final String PASSWORD = ActiveMQConnection.DEFAULT_PASSWORD;//默认连接密码
    private static final String BROKEURL = ActiveMQConnection.DEFAULT_BROKER_URL;//默认连接地址


    public static void main(String[] args) {
        ConnectionFactory connectionFactory;//连接工厂
        Connection connection = null;//连接

        Session session;//会话 接受或者发送消息的线程
        Destination destination;//消息的目的地

        MessageConsumer messageConsumer;//消息的消费者

        //实例化连接工厂
        connectionFactory = new ActiveMQConnectionFactory(JmsConsumer.USERNAME,
                JmsConsumer.PASSWORD, JmsConsumer.BROKEURL);

        try {
            //通过连接工厂获取连接
            connection = connectionFactory.createConnection();
            //启动连接
            connection.start();
            //创建session
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            //创建一个连接HelloWorld的消息队列
            destination = session.createQueue("HelloWAM");//点对点消息队列，如果要创建发布订阅模式，是要改成session.createTopic("HelloWAM");，整个类其他地方都不用变

            //创建消息消费者
            messageConsumer = session.createConsumer(destination);

            //读取消息
            while (true) {
                TextMessage textMessage = (TextMessage) messageConsumer.receive(10000);
                if (textMessage != null) {
                    System.out.println("Accept msg : " + textMessage.getText());
                } else {
                    break;
                }
            }
        } catch (JMSException e) {
            e.printStackTrace();
        }

    }
}
