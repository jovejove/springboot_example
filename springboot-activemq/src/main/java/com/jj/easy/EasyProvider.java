package com.jj.easy;

import com.alibaba.fastjson.JSON;
import com.jj.common.Driver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @ClassName: EasyProvider.java
 * @Author: jjl
 * @Description:
 * @Date: 2021-09-25
 * @Version: 1.0
 */
@Service
public class EasyProvider {

//    @Resource
//    private JmsMessagingTemplate jmsMessagingTemplate;
    @Resource
    private JmsTemplate jmsTemplate;

    @Scheduled(cron = "0/2 * * * * ?")
    public void send() {
        for (int i = 0; i < 3; i++) {
            jmsTemplate.convertAndSend("active_easy_queue", JSON.toJSONString(new Driver(1L, "小司机")));
        }
    }
}
