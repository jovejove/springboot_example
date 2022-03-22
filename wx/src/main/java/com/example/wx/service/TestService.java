package com.example.wx.service;

import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateData;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateMessage;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author: ljj
 * @date: 2022/3/21 16:22
 * @description:
 */

@Service
public class TestService {

    @Resource
    private WxMpService wxMpService;

    public void test2() {
        WxMpTemplateMessage templateMessage = WxMpTemplateMessage.builder()
                .toUser("ocprz5qQzEs0DNvTVo1AASMVARBE").templateId("n_-_Bb04TpKXtR7LWopJdRX6J1F1gthmRzntjCYJ8gk").url(null).build();
        templateMessage.addData(new WxMpTemplateData("name", "巧克力", "#173177"));
        templateMessage.addData(new WxMpTemplateData("pandaName", "39.8元", "#ff4d00"));

        try {
            wxMpService.getTemplateMsgService().sendTemplateMsg(templateMessage);
        } catch (WxErrorException e) {
            e.printStackTrace();
        }
    }
}
