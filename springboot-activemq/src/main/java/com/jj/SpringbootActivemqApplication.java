package com.jj;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class SpringbootActivemqApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootActivemqApplication.class, args);
    }

}
