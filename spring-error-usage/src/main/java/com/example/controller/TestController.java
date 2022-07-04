package com.example.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: ljj
 * @date: 2022/6/29
 * @description:
 */
@RestController
public class TestController {

    @RequestMapping("/")
    private Object test() {
        return "test";
    }
}
