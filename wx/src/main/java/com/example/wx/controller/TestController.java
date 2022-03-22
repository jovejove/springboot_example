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

/**
 * @author: ljj
 * @date: 2022/3/21 11:51
 * @description:
 */
@RestController
@Slf4j
@RequestMapping("/wx")
public class TestController {



    public boolean sendTemplateMsg(String appid, String openId, String templateId, List<WxMpTemplateData> data, String wxMpStateBusinessType, List<String> wxMpStateUrlParamValueList){

        return true;
    }

    @Resource
    private WxMpService wxMpService;

    @PostMapping("/test")
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
