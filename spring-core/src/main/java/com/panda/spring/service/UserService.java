package com.panda.spring.service;

import com.panda.spring.entity.User;

import java.util.List;

public interface UserService {

    boolean save();

    List<User> listUser();
}
