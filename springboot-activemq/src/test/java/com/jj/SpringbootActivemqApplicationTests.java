package com.jj;

import com.jj.easy.EasyProvider;
import com.jj.topic.ActiveTopicProvider;
import com.jj.virtual.VirtualProvider;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringbootActivemqApplicationTests {

    @Autowired
    private EasyProvider easyProvider;

    @Autowired
    private ActiveTopicProvider activeTopicProvider;

    @Autowired
    private VirtualProvider virtualProvider;

    @Test
    void contextLoads() {
        easyProvider.send();
    }

    @Test
    void test() {
        activeTopicProvider.sendMessage();
    }

    @Test
    void test2() {
        virtualProvider.sendTopicMessage("aaa");
    }

}
