package com.example.service.impl;

import com.alibaba.fastjson.JSON;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class JobStatusTraceLogServiceImplTest {

    @Resource
    private JobStatusTraceLogServiceImpl jobStatusTraceLogService;

    @Test
    public void test() {

        System.out.println(111);

        int count = jobStatusTraceLogService.count();

        System.out.println(JSON.toJSONString(count));

    }
}