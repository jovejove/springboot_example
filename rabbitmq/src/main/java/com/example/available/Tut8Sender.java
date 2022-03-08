package com.example.available;

import com.example.callback.Tut8SenderCallback;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import javax.annotation.Resource;

@Slf4j
public class Tut8Sender {

    @Resource
    Tut8SenderCallback tut8SenderCallback;

    @Resource
    private Queue queue;

    @Resource
    private RabbitTemplate template;

//    @Scheduled(fixedDelay = 1000, initialDelay = 500)
    public void send() {
        String message = "available queue!";
        this.template.convertAndSend(queue.getName(), message);
        System.out.println(" [x] Sent '" + message + "'");
        log.info("消息发送完毕");
        template.setReturnsCallback(tut8SenderCallback);
        template.setConfirmCallback(tut8SenderCallback);
    }
}
