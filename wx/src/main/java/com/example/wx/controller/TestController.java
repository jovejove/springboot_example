package com.example.wx.controller;

import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.WxMpTemplateMsgService;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateData;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author: ljj
 * @date: 2022/3/21 11:51
 * @description:
 */
@RestController
@Slf4j
@RequestMapping("/wx")
public class TestController {


    public boolean sendTemplateMsg(String appid, String openId, String templateId, List<WxMpTemplateData> data, String wxMpStateBusinessType, List<String> wxMpStateUrlParamValueList) {

        return true;
    }

    @Resource
    private WxMpService wxMpService;


    @PostMapping("/test")
    public Object test2() throws InterruptedException {
        String templateId = "eaKQWFcesrnKtE34j2jFvoAbjBU0YPu-QKQuhrQ4D2g";
        String templateId2 = "n_-_Bb04TpKXtR7LWopJdRX6J1F1gthmRzntjCYJ8gk";
        final AtomicInteger count = new AtomicInteger(0);



//        ExecutorService threadPool = Executors.newCachedThreadPool();
        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(100, 200, 60, TimeUnit.SECONDS,  new SynchronousQueue<Runnable>());
        int size = 1000;
        CountDownLatch countDownLatch = new CountDownLatch(size);
        for (int i = 0; i < size; i++) {
            threadPool.execute(() -> {
                try {
                    int get = count.incrementAndGet();
                    System.out.println("AtomicInteger:" + get);
                    WxMpTemplateMessage templateMessage = WxMpTemplateMessage.builder()
                            .toUser("ocprz5qQzEs0DNvTVo1AASMVARBE").templateId(templateId).url(null).build();
                    templateMessage.addData(new WxMpTemplateData("name", "巧克力", "#173177"));
                    templateMessage.addData(new WxMpTemplateData("pandaName", "小猫"+count.get(), "#ff4d00"));
                    wxMpService.getTemplateMsgService().sendTemplateMsg(templateMessage);
                } catch (WxErrorException e) {
                    e.printStackTrace();
                }finally {
                    countDownLatch.countDown();
                }
            });
        }
        countDownLatch.await();
        return count.get();
    }
}
