package com.panda.demo.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.tags.Param;

@RestController
@RequestMapping(value = "/test")
public class TestXssController {

    @PostMapping(value = "/xss")
    public Object test(String name) {
        System.out.println(name);
        return String.format("<h1>%s</h1>", name);
    }

    @PostMapping(value = "/json")
    public Object testJSON(@RequestBody Param param) {
        System.out.println(param.toString());
        return param;
    }

    @GetMapping(value = "/query")
    public Object testQuery(String q) {
        System.out.println(q);
        return String.format("<h1>%s</h1>", q);
    }

    @PostMapping(value = "/upload")
    public Object upload(MultipartFile file) {
        System.out.println(file.getOriginalFilename());
        return "OK";
    }

}
