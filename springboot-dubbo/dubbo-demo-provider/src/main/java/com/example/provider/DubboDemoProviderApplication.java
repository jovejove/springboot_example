package com.example.provider;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

import javax.annotation.PreDestroy;

@EnableDubbo(scanBasePackages = "com.example.provider.service.impl")
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class DubboDemoProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(DubboDemoProviderApplication.class, args);
    }

//    public static void main(String[] args) throws Exception {
//        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ProviderConfiguration.class);
//        context.start();
//        System.in.read();
//    }

//    @Configuration
//    @EnableDubbo(scanBasePackages = "org.apache.dubbo.demo.provider")
//    @PropertySource("classpath:/spring/dubbo-provider.properties")
//    static class ProviderConfiguration {
//        @Bean
//        public RegistryConfig registryConfig() {
//            RegistryConfig registryConfig = new RegistryConfig();
//            registryConfig.setAddress("zookeeper://127.0.0.1:2181");
//            return registryConfig;
//        }
//    }



}
