package com.panda.demo.controller;

import com.panda.demo.service.NbaPlayerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class NbaPlayerController {
    @Autowired
    NbaPlayerService nbaPlayerService;

    @GetMapping("/queryAllData")
    public Object queryAllData() {
        return nbaPlayerService.queryAllData();
    }

    @GetMapping("/queryById")
    public Object queryById(String id) {
        return nbaPlayerService.queryById(id);
    }

}