package com.example.available;

import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

@Slf4j
public class Tut8Receiver {

    /**
     * 手动应答模式
     * @param object
     * @param message
     * @param channel
     */
//    @RabbitListener(queues = "available")
    public void receiveManual(String object, Message message, Channel channel) {
        long deliveryTag = message.getMessageProperties().getDeliveryTag();
        log.info("消费成功：{},消息内容:{}", deliveryTag, object);
        try {
            // todo 执行业务代码...
            int i = 1 / 0; //故意报错测试
            channel.basicAck(deliveryTag, false);
        } catch (Exception e) {
            log.error("签收失败", e);
            try {
                /**
                 * 第三个参数代表是否重回队列 false=丢弃  true=重回消息队列顶端, 通常代码的报错并不会因为重试就能解决,极大可能出现死循环(生产环境建议不使用)
                 * 3个解决方案:
                 * 当消费失败后将此消息存到 Redis，记录消费次数，如果消费了三次还是失败，就丢弃掉消息，记录日志落库保存
                 * 直接填 false ，不重回队列，记录日志、发送邮件等待开发手动处理
                 * 不启用手动 ack ，使用 SpringBoot 提供的消息重试
                 */
                channel.basicNack(deliveryTag, false, false);
            } catch (Exception exception) {
                log.error("拒签失败", exception);
            }
            // todo 记录日志、发送邮件、保存消息到数据库，落库之前判断如果消息已经落库就不保存
            throw new RuntimeException("消息消费失败");
        }
    }
    /**
     * 自动应答模式
     * @param object
     * @param message
     * @param channel
     */
    @RabbitListener(queues = "available")
    public void receiveAutoAck(String object, Message message, Channel channel) {
        long deliveryTag = message.getMessageProperties().getDeliveryTag();
        log.info("消费成功：{},消息内容:{}", deliveryTag, object);
        try {
            // todo 执行业务代码...
//            int i = 1 / 0; //故意报错测试
        } catch (Exception e) {
            log.error("签收失败", e);
            // todo 记录日志、发送邮件、保存消息到数据库，落库之前判断如果消息已经落库就不保存
            throw new RuntimeException("消息消费失败");
        }
    }
}
