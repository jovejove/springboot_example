package com.jj.topic;

import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;

import javax.jms.ConnectionFactory;
import javax.jms.Topic;

/**
 * @ClassName: ActiveTopicConfig.java
 * @Author: jjl
 * @Description:
 * @Date: 2021-09-25
 * @Version: 1.0
 */

/**
 * topic 主题模式 与点对点模式最大的区别是 其可以进行一对多，多个消费者监听到topic，即可同时收到消息
 */
@Configuration
public class ActiveTopicConfig {
    @Bean
    public Topic topic() {
        return new ActiveMQTopic("jj_topic");
    }

    /**
     * springboot默认只配置queue类型消息，如果要使用topic类型的消息，则需要配置该bean
     *
     * @param connectionFactory factory.setPubSubDomain(true);
     */
    @Bean
    public JmsListenerContainerFactory jjTopicFactory(ConnectionFactory connectionFactory) {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        //这里必须设置为true，false则表示是queue类型 虚拟主题（virtual则必须设为false）
        factory.setPubSubDomain(true);
        return factory;
    }
}
