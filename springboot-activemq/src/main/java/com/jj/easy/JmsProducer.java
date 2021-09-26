package com.jj.easy;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * @ClassName: JmsProducer.java
 * @Author: jjl
 * @Description:
 * @Date: 2021-09-26
 * @Version: 1.0
 */
public class JmsProducer {

    //默认连接用户名
    private static final String USERNAME = ActiveMQConnection.DEFAULT_USER;
    //默认连接密码
    private static final String PASSWORD = ActiveMQConnection.DEFAULT_PASSWORD;
    //默认连接地址
    private static final String BROKEURL = ActiveMQConnection.DEFAULT_BROKER_URL;
    //发送的消息数量
    private static final int SENDNUM = 10;

    public static void main(String[] args) {
        ConnectionFactory connectionFactory;//连接工厂
        Connection connection = null;//连接

        Session session;//会话 接受或者发送消息的线程
        Destination destination;//消息的目的地

        MessageProducer messageProducer;

        connectionFactory = new ActiveMQConnectionFactory(USERNAME, PASSWORD, BROKEURL);
        try {
            connection = connectionFactory.createConnection();//连接
            connection.start();
                        /*
            createSession参数取值
            * 1、为true表示启用事务
            * 2、消息的确认模式
            * AUTO_ACKNOWLEDGE  自动签收
            * CLIENT_ACKNOWLEDGE 客户端自行调用acknowledge方法签收
            * DUPS_OK_ACKNOWLEDGE 不是必须签收，消费可能会重复发送
            * 在第二次重新传送消息的时候，消息
               头的JmsDelivered会被置为true标示当前消息已经传送过一次，
               客户端需要进行消息的重复处理控制。
            * */
            session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);//会话
            destination = session.createQueue("HelloWAM");//点对点消息队列，如果要创建发布订阅模式，是要改成session.createTopic("HelloWAM");，整个类其他地方都不用变
            messageProducer = session.createProducer(destination);//消息生产者
            for (int i = 0; i < SENDNUM; i++) {
                String msg = "发送消息" + i + " " + System.currentTimeMillis();
                TextMessage message = session.createTextMessage(msg);
                System.out.println("发送消息:" + msg);
                messageProducer.send(message);
            }
            session.commit();

        } catch (JMSException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                try {
                    connection.close();
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
