package com.example.error.service.aop;

import com.example.error.entity.User;
import org.springframework.stereotype.Service;

@Service
public class AdminUserService {
    public final User adminUser = new User("202101166");
    
    public void login() {
        System.out.println("admin user login...");
    }

    public User getAdminUser() {
        return adminUser;
    }
}