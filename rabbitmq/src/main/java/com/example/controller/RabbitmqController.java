package com.example.controller;

import com.example.available.Tut8Sender;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author Administrator
 */
@RestController
@RequestMapping("/")
public class RabbitmqController {


    @Resource
    Tut8Sender tut8Sender;

    @PostMapping("/available")
    public Object available() {
        tut8Sender.send();
        return "available";
    }
}
