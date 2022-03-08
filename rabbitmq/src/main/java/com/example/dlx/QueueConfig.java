package com.example.dlx;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.util.HashMap;

/**
 * @author Administrator
 */
@Configuration
public class QueueConfig {

    static final String DIRECT_DEAD_EXCHANGE = "direct-dead-exchange";
    static final String DIRECT_TTL_EXCHANGE = "direct-ttl-exchange";
    static final String DIRECT_EXCEPTION_EXCHANGE = "direct-exception-exchange";

    static final String DEAD_QUEUE = "direct-dead-queue";
    static final String DIRECT_EXCEPTION_QUEUE = "direct-exception-queue";


    /**
     * 定义一个死信交换机，类型为Direct
     */
    @Bean
    public DirectExchange deadDirectExchange() {
        return new DirectExchange(DIRECT_DEAD_EXCHANGE, true, false);
    }

    /**
     * 定义一个死信队列，用来接收重新投递的死信
     */
    @Primary
    @Bean
    public Queue deadQueue() {
        return new Queue(DEAD_QUEUE, true, false, false);
    }

    /**
     * 将死信队列绑定到交换机上,指定binding routing key为dead
     */
    @Bean
    public Binding bindingDead(@Qualifier("deadQueue") Queue deadQueue,@Qualifier("deadDirectExchange") DirectExchange deadDirectExchange) {
        return BindingBuilder.bind(deadQueue).to(deadDirectExchange).with("dead");
    }


    /**
     * 定义一个Direct类型的交换机
     */
    @Bean
    public DirectExchange ttlDirectExchange() {
        return new DirectExchange(DIRECT_TTL_EXCHANGE, true, false);
    }

    /**
     * 定义一个指定过期时间的的队列(并配置参数)
     */
    @Bean
    public Queue ttlQueue() {
        HashMap<String, Object> args = new HashMap<>();
        //队列中消息的过期时间
        args.put("x-message-ttl", 10000);
        //当消息变为死信后重新发送到指定死信交换机
        args.put("x-dead-letter-exchange", DIRECT_DEAD_EXCHANGE);
        //当死信到达死信交换机后，根据该路由键投递到指定的死信队列
        args.put("x-dead-letter-routing-key", "dead");
        return new Queue("direct-ttl-queue", true, false, false, args);
    }

    /**
     * 将ttlQueue队列绑定到交换机ttlDirectExchange上,指定binding routing 为 ttl
     */
    @Bean
    public Binding bindingTll(@Qualifier("ttlQueue") Queue ttlQueue,@Qualifier("ttlDirectExchange") DirectExchange ttlDirectExchange) {
        return BindingBuilder.bind(ttlQueue).to(ttlDirectExchange).with("ttl");
    }


    /**
     * 定义一个Direct类型的交换机
     */
    @Bean
    public DirectExchange exceptionDirectExchange() {
        return new DirectExchange(DIRECT_EXCEPTION_EXCHANGE, true, false);
    }

    /**
     * 定义一个指定过期时间的的队列(并配置参数)
     */
    @Bean
    public Queue exceptionQueue() {
        HashMap<String, Object> args = new HashMap<>();
        //队列中消息的过期时间
        args.put("x-message-ttl", 3*24*60*60*1000);
        //当消息变为死信后重新发送到指定死信交换机
        args.put("x-dead-letter-exchange", DIRECT_DEAD_EXCHANGE);
        //当死信到达死信交换机后，根据该路由键投递到指定的死信队列
        args.put("x-dead-letter-routing-key", "dead");
        return new Queue(DIRECT_EXCEPTION_QUEUE, true, false, false, args);
    }

    /**
     * 将exceptionQueue队列绑定到交换机exceptionDirectExchange上,指定binding routing 为 exception
     */
    @Bean
    public Binding bindingException(@Qualifier("exceptionQueue") Queue exceptionQueue,@Qualifier("exceptionDirectExchange") DirectExchange exceptionDirectExchange) {
        return BindingBuilder.bind(exceptionQueue).to(exceptionDirectExchange).with("exception");
    }

}
