package com.example.dlx;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

import static com.example.dlx.QueueConfig.DIRECT_EXCEPTION_EXCHANGE;
import static com.example.dlx.QueueConfig.DIRECT_TTL_EXCHANGE;

/**
 * @author ljj
 */
@RestController
@RequestMapping("dlx")
public class Controller {
    @Resource
    private RabbitTemplate rabbitTemplate;

    /**
     * 延时队列
     */
    @PostMapping("/ttl")
    public Object ttl() {
        String routingKey="ttl";
        rabbitTemplate.convertAndSend(DIRECT_TTL_EXCHANGE,routingKey,"延迟队列测试---不喝奶茶的monkey");
        return "延迟队列测试";
    }
    /**
     * 队列出现异常情况
     */
    @PostMapping("/exception")
    public Object exception() {
        String routingKey="exception";
        rabbitTemplate.convertAndSend(DIRECT_EXCEPTION_EXCHANGE,routingKey,"死信队列测试---不喝奶茶的monkey2deadletter");
        return "死信队列测试";
    }
}
