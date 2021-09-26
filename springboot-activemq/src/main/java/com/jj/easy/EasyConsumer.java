package com.jj.easy;

import com.alibaba.fastjson.JSON;
import com.jj.common.Driver;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
 * @ClassName: EasyConsumer.java
 * @Author: jjl
 * @Description:
 * @Date: 2021-09-25
 * @Version: 1.0
 */

@Component
public class EasyConsumer {

    @JmsListener(destination = "active_easy_queue")
    public void aa(String aa) {
        System.out.println("收到简单的消息" + JSON.parseObject(aa, Driver.class));
    }
}
