package com.panda.spring;


import com.panda.spring.entity.ExampleBean;
import com.panda.spring.entity.InstantiationTracingBeanPostProcessor;
import com.panda.spring.entity.MovieRecommender;
import com.panda.spring.entity.Person;
import com.panda.spring.service.ClientService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
class TestApplicationTest {

    @Resource
    private ExampleBean exampleBean;

    @Resource
    private ClientService clientService;

    @Resource
    private InstantiationTracingBeanPostProcessor instanceBeanPostProcessor;

    @Resource
    private MovieRecommender movieRecommender;

    @Resource
    private Person person;

    @Test
    void contextLoads() {
//        System.out.println(exampleBean.toString());

//        System.out.println(clientService);

        System.out.println(person);

//        System.out.println(movieRecommender.getCatalog());

    }

}