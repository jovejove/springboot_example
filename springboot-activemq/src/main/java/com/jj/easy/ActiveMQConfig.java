package com.jj.easy;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.ActiveMQSession;
import org.apache.activemq.RedeliveryPolicy;
import org.apache.activemq.pool.PooledConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.config.SimpleJmsListenerContainerFactory;
import org.springframework.jms.core.JmsTemplate;

import javax.jms.ConnectionFactory;
import javax.jms.DeliveryMode;

/**
 * 
 * MQ队列配置
 */
@Configuration
public class ActiveMQConfig {
	
    @Value("${spring.activemq.user}")
    private String usrName;

    @Value("${spring.activemq.password}")
    private  String password;

    @Value("${spring.activemq.broker-url}")
    private  String brokerUrl;

    /**
     * 重发策略
     */
    @Bean
    public RedeliveryPolicy redeliveryPolicy(){
    	
    	RedeliveryPolicy  redeliveryPolicy=   new RedeliveryPolicy();
    	//是否在每次尝试重新发送失败后,增长这个等待时间
    	redeliveryPolicy.setUseExponentialBackOff(true);
    	//重发次数,默认为6次   这里设置为5次
    	redeliveryPolicy.setMaximumRedeliveries(5);
    	//重发时间间隔,默认为1秒
    	redeliveryPolicy.setInitialRedeliveryDelay(2*1000l);
    	//第一次失败后重新发送之前等待500毫秒,第二次失败再等待500 * 2毫秒,这里的2就是value
    	redeliveryPolicy.setBackOffMultiplier(2);
    	//是否避免消息碰撞
    	redeliveryPolicy.setUseCollisionAvoidance(false);
    	//设置重发最大拖延时间-1 表示没有拖延只有UseExponentialBackOff(true)为true时生效
    	redeliveryPolicy.setMaximumRedeliveryDelay(-1);
    	return redeliveryPolicy;
    }

    @Bean
    public PooledConnectionFactory connectionFactory(RedeliveryPolicy redeliveryPolicy) {
        ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory(usrName, password, brokerUrl);
        activeMQConnectionFactory.setRedeliveryPolicy(redeliveryPolicy);
        activeMQConnectionFactory.setUseAsyncSend(true);

        PooledConnectionFactory pooledConnectionFactory = new PooledConnectionFactory(activeMQConnectionFactory);
        pooledConnectionFactory.setMaxConnections(8);
        return pooledConnectionFactory;
    }
    
    @Bean
    public JmsTemplate jmsTemplate(PooledConnectionFactory activeMQConnectionFactory){
    	JmsTemplate jmsTemplate=new JmsTemplate();
        jmsTemplate.setDeliveryMode(DeliveryMode.PERSISTENT);//进行持久化配置 1表示非持久化，2表示持久化
    	jmsTemplate.setConnectionFactory(activeMQConnectionFactory);
    	jmsTemplate.setSessionAcknowledgeMode(4);//客户端签收模式,单条消息确认 activemq 独有
    	jmsTemplate.setReceiveTimeout(15 * 1000L);
    	return jmsTemplate;
    }
    
    /**
     * 
     * 定义一个消息监听器连接工厂，这里定义的是点对点模式的监听器连接工厂
     * 
     * @param activeMQConnectionFactory
     * @return
     */
    @Bean(name = "jmsQueueListener")
    public DefaultJmsListenerContainerFactory jmsQueueListenerContainerFactory(PooledConnectionFactory activeMQConnectionFactory) {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        factory.setConnectionFactory(activeMQConnectionFactory);
        factory.setSessionTransacted(false);
        //设置连接数（并发数）
        factory.setConcurrency("1-10");
        //重连间隔时间
        factory.setRecoveryInterval(1000L);
        //客户端签收模式,单条消息确认 activemq 独有
        factory.setSessionAcknowledgeMode(4);
        return factory;
    }

    @Bean(name = "jmsQueueListenerTx")
    public DefaultJmsListenerContainerFactory jmsQueueListenerContainerFactoryTx(PooledConnectionFactory activeMQConnectionFactory) {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        factory.setConnectionFactory(activeMQConnectionFactory);
        // 开启事务
        factory.setSessionTransacted(true);
        // 设置手动确认，默认配置中Session是开启了事物的，即使我们设置了手动Ack也是无效的
//        factory.setSessionAcknowledgeMode(ActiveMQSession.INDIVIDUAL_ACKNOWLEDGE);
        // false 队列模式  true 主题模式
        factory.setPubSubDomain(false);
        //设置连接数（并发数）
        factory.setConcurrency("1-10");
        //重连间隔时间
        factory.setRecoveryInterval(1000L);
        //客户端签收模式,单条消息确认 activemq 独有
        factory.setSessionAcknowledgeMode(4);
        return factory;
    }


    /**
     * 在Topic模式中，对消息的监听需要对containerFactory进行配置
     * @param connectionFactory
     * @return
     */
    @Bean("topicListener")
    public JmsListenerContainerFactory<?> topicJmsListenerContainerFactory(ConnectionFactory connectionFactory) {
        SimpleJmsListenerContainerFactory factory = new SimpleJmsListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setPubSubDomain(true);
        return factory;
    }

}
