package com.example.callback;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.ReturnedMessage;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

/**
 * @author Administrator
 */
@Slf4j
@Component
public class Tut8SenderCallback implements RabbitTemplate.ConfirmCallback, RabbitTemplate.ReturnsCallback {
    /**
     * confirm 监听，当消息成功发到交换机 ack = true，没有发送到交换机 ack = false
     * @param correlationData 可在发送时指定消息唯一 id
     * @param ack 应答标志
     * @param cause 失败原因
     */
    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        log.info("CorrelationData content : " + correlationData);
        log.info("exchange Ack status : " + ack);
        log.info("Cause content : " + cause);
        if(ack){
            log.info("消息成功发到交换机ack:"+ true);
        }else{
            //记录日志、发送邮件通知、落库定时任务扫描重发
            log.info("消息发送失败："+correlationData+", 出现异常："+cause);
        }
    }

    /**
     * 当消息成功发送到交换机没有路由到队列触发此监听
     */
    @Override
    public void returnedMessage(ReturnedMessage message) {
        //记录日志、发送邮件通知、落库定时任务扫描重发
        System.err.println("ReturnedMessage: " + message);
    }
}
