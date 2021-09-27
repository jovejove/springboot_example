package com.example.consumer;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@EnableDubbo(scanBasePackages = "com.example.consumer.service.impl")
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class DubboDemoConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(DubboDemoConsumerApplication.class, args);
    }

//    public static void main(String[] args) {
//        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ConsumerConfiguration.class);
//        context.start();
//        DemoService service = context.getBean("demoServiceComponent", DemoServiceComponent.class);
//        String hello = service.sayHello("world");
//        System.out.println("result :" + hello);
//    }
//
//    @Configuration
//    @EnableDubbo(scanBasePackages = "com.example.consumer")
//    @PropertySource("classpath:/spring/dubbo-consumer.properties")
//    @ComponentScan(value = {"com.example.consumer"})
//    static class ConsumerConfiguration {
//
//    }
}
