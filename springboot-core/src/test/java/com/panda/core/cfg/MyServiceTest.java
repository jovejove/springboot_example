package com.panda.core.cfg;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class MyServiceTest {

    @Resource
    private MyService myService;

    @Test
    void test() {
        myService.openConnection();
    }
}