package com.example.dlx;

import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.time.LocalDateTime;

import static com.example.dlx.QueueConfig.DEAD_QUEUE;
import static com.example.dlx.QueueConfig.DIRECT_EXCEPTION_QUEUE;


/**
 * @author ljj
 */
@Slf4j
@Component
public class Consumer {

    /**
     * 延时队列=死信队列+ttl
     *
     * @param msg     被消费的消息
     * @param channel 通道
     * @param message 消息对象
     */
    @RabbitListener(queues = {DEAD_QUEUE})
    public void delay(Object msg, Channel channel, Message message) throws IOException {
        System.out.println("消费时间：" + LocalDateTime.now());
        System.out.println("消费者接收到的消息：" + msg);
        System.out.println("消费者接收到的消息：" + new String(message.getBody()));
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
    }

    /**
     * 死信队列
     *
     * @param msg     被消费的消息
     * @param channel 通道
     * @param message 被消费的消息对象
     */
    @RabbitListener(queues = {DIRECT_EXCEPTION_QUEUE})
    public void dlx(String msg, Channel channel, Message message) throws IOException {
        log.info("收到业务消息A：{}", msg);
        boolean ack = true;
        Exception exception = null;

        // 出现未捕获的异常会触发springboot重试机制(需配置),超过重试次数消息进入死信队列,如未配置,则不会重试直接进入死信队列
//        System.out.println(1 / 0);

        try {
            if (msg.contains("deadletter")) {
                throw new RuntimeException("dead letter exception");
            }
        } catch (Exception e) {
            ack = false;
            exception = e;
        }
        if (!ack) {
            // todo 打印日志,发邮件,钉钉等通知运维人员
            log.error("消息消费发生异常，error msg:{}", exception.getMessage(), exception);
            // 需开启手动确认模式 acknowledge-mode: manual 进入死信队列
            // channel.basicReject(message.getMessageProperties().getDeliveryTag(),false);
            channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, false);
        } else {
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        }
    }
}
