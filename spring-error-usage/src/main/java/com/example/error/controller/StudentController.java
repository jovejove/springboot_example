package com.example.error.controller;

import com.example.error.service.define.DataService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
@Validated
public class StudentController {
    @Resource
    DataService dataService;

    @RequestMapping(path = "students/{id}", method = RequestMethod.DELETE)
    public void deleteStudent(@PathVariable("id") int id) {
        dataService.deleteStudent(id);
    }


    @PostMapping("/regStudent/{name}")
    @ResponseBody
    public String saveUser(@PathVariable(value = "name") String name) throws Exception {
        System.out.println("用户注册成功:"+name);
        return "success";
    }



}