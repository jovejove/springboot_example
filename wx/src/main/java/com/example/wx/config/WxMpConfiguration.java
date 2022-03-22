package com.example.wx.config;

import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import me.chanjar.weixin.mp.config.WxMpConfigStorage;
import me.chanjar.weixin.mp.config.impl.WxMpDefaultConfigImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.Executors;


@Configuration
@EnableConfigurationProperties(WxMpProperties.class)
public class WxMpConfiguration {
    @Bean
    public WxMpService wxMpService() {
        WxMpService service = new WxMpServiceImpl();
        WxMpDefaultConfigImpl wxMpDefaultConfig = new WxMpDefaultConfigImpl();
//        service.setMultiConfigStorages(configs
//            .stream().map(a -> {
//                    WxMpDefaultConfigImpl configStorage = wxMpDefaultConfig;
//                configStorage.setAppId(a.getAppId());
//                configStorage.setSecret(a.getSecret());
//                configStorage.setToken(a.getToken());
//                configStorage.setAesKey(a.getAesKey());
//                return configStorage;
//            }).collect(Collectors.toMap(WxMpDefaultConfigImpl::getAppId, a -> a, (o, n) -> o)));
        wxMpDefaultConfig.setAppId("wx0389f510313ee66b");
        wxMpDefaultConfig.setSecret("e7f0dee547e214de47aed977fec6c187");
//        wxMpDefaultConfig.setToken();
//        wxMpDefaultConfig.setAesKey();
        service.setWxMpConfigStorage(wxMpDefaultConfig);


        return service;
    }


}