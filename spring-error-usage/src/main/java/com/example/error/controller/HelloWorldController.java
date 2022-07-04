package com.example.error.controller;

import com.example.error.even.MyEvent;
import com.example.error.service.aop.ElectricService;
import com.example.error.service.define.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.UUID;

@Slf4j
@RestController
public class HelloWorldController {

    @Resource
    private ServiceImpl serviceImpl;

//    @Autowired
//    private ApplicationContext applicationContext;

//    public ServiceImpl getServiceImpl() {
//        return applicationContext.getBean(ServiceImpl.class);
//    }

    @RequestMapping(path = "/hi", method = RequestMethod.POST)
    public String hi() {
        return "helloworld, service is : " + serviceImpl;
    }

//    @Lookup
//    public ServiceImpl getServiceImpl(){ return null; }

    @Resource
    ElectricService electricService;

    @RequestMapping(path = "charge", method = RequestMethod.GET)
    public void charge() throws Exception {
        electricService.charge();
    }

    @Autowired
    private AbstractApplicationContext applicationContext;

//    @RequestMapping(path = "publishEvent", method = RequestMethod.GET)
//    public String notifyEvent() {
//        applicationContext.start();
//        return "ok";
//    }



    @RequestMapping(path = "publishEvent", method = RequestMethod.GET)
    public String notifyEvent() {
        log.info("start to publish event");
        applicationContext.publishEvent(new MyEvent(UUID.randomUUID()));
        return "ok";
    }
}