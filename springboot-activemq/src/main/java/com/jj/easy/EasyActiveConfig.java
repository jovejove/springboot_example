package com.jj.easy;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.jms.Queue;

/**
 * @ClassName: EasyActiveConfig.java
 * @Author: jjl
 * @Description:
 * @Date: 2021-09-25
 * @Version: 1.0
 */

/**
 * 点对点模式
 */
@Configuration
public class EasyActiveConfig {

    @Bean
    public Queue easyQueue() {
        return new ActiveMQQueue("active_easy_queue");
    }
}
