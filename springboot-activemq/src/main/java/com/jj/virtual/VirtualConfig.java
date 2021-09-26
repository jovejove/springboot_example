package com.jj.virtual;

import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;

import javax.jms.ConnectionFactory;

/**
 * @ClassName: VirtualConfig.java
 * @Author: jjl
 * @Description:
 * @Date: 2021-09-26
 * @Version: 1.0
 */

/**
 * topic的工作模式：
 * <p>
 * 使用topic方式，那么如果消费端部署的是集群方式，那么每一个都订阅了，在发送消息的时候，
 * 集群中的每一个订阅者都有可能收到，但这肯定不会是我们想要的结果.
 * <p>
 * 所以virtualtopic这个东西,这个东西我们可以理解为是queue和topic的结合，
 * 使用topic的一对多的广播功能，又满足了消费者在做了集群的时候，只有一个收到，也就是队列的一对一的特效。
 * <p>
 * 配置
 * <p>
 * 需要注意的是 activemq中 并没有 virtualTopic的构造方法，
 * 其更像是一个约定，只要topic 名字是 VirtualTopic.xxx 其就会使其成为一个虚拟主题
 * <p>
 * factory.setPubSubDomain(false); 这一步尤为重要（实质queue模式则就是设置的false），否则消费者收不到消息
 */
@EnableJms
@Configuration
public class VirtualConfig {

    private static final String VIRTUAL_TOPIC_NAME = "VirtualTopic.Orders";


    @Bean("virtualTopicQueue")
    public ActiveMQTopic virtualTopicQueue() {
        return new ActiveMQTopic(VIRTUAL_TOPIC_NAME);
    }

    /**
     * 特别特别注意配置虚拟主题 需要设为false
     */
    @Bean(name = "virtualFactory")
    public JmsListenerContainerFactory<?> virtualFactory(ConnectionFactory connectionFactory) {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        factory.setPubSubDomain(false);
        factory.setConnectionFactory(connectionFactory);
        return factory;
    }
}
