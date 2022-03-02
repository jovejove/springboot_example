package com.panda.spring.service.impl;

import com.panda.spring.entity.User;
import com.panda.spring.service.UserService;

import java.util.Collections;
import java.util.Date;
import java.util.List;

public class UserServiceImpl implements UserService {
    @Override
    public boolean save() {
        return false;
    }

    @Override
    public List<User> listUser() {
        User user = new User();
        user.setName("panda");
        user.setAge(18);
        user.setBirthday(new Date());
        return Collections.singletonList(user);
    }

}
