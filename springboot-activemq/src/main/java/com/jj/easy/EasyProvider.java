package com.jj.easy;

import com.alibaba.fastjson.JSON;
import com.jj.common.Driver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Service;

/**
 * @ClassName: EasyProvider.java
 * @Author: jjl
 * @Description:
 * @Date: 2021-09-25
 * @Version: 1.0
 */
@Service
public class EasyProvider {

    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    public void send() {
        for (int i = 0; i < 3; i++) {
            jmsMessagingTemplate.convertAndSend("active_easy_queue", JSON.toJSONString(new Driver(1L, "小司机")));
        }
    }
}
