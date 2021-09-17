package com.panda.demo.controller;

import com.panda.demo.entity.TUniUser;
import com.panda.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/getUserById")
    public String getUserById(Integer id) {
        return userService.getUserById(id);
    }

    @GetMapping("/deleteUserById")
    public void deleteUserById(Integer id) {
        userService.deleteUserById(id);
    }

    @GetMapping("/queryAllUser")
    @ResponseBody
    public List<TUniUser> queryAllUser() {
        return userService.queryAllUser();
    }

    @GetMapping("/queryUserByName")
    @ResponseBody
    public TUniUser queryUserByName(String empName) {
        return userService.queryUserByName(empName);
    }

    @GetMapping("/queryUserByName2")
    @ResponseBody
    public TUniUser queryUserByName2(String empName) {
        return userService.queryUserByName2(empName);
    }


}