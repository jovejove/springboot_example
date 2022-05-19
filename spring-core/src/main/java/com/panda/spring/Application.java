package com.panda.spring;

import com.alibaba.fastjson.JSONObject;
import com.panda.spring.life.MyService;
import com.panda.spring.life.MyServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ImportResource;

/**
 * @author Administrator
 */
//@ImportResource(value = "classpath:beans.xml")
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);
        MyService exampleBean = context.getBean(MyServiceImpl.class);
        System.out.println("finalBean:"+ JSONObject.toJSONString(exampleBean));
    }


}
