package com.example.spock.controller;


import com.example.spock.entity.DemoEntity;
import com.example.spock.service.DemoService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/demo")
public class DemoController {


    @Resource
    private DemoService demoService;

    @PostMapping()
    public DemoEntity getDemo(Integer demoId) {
        return demoService.getDemo(demoId);
    }

}

