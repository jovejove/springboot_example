package com.example.java;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JavaApplication {

    public static void main(String[] args) {
//        SpringApplication.run(JavaApplication.class, args);

        while (true) {
            System.out.println("aaaa");
            if (true) {
                break;
            }
            System.out.println("bbbb");
        }
    }


}
