package com.example.error.service.aop;

import org.springframework.aop.framework.AopContext;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class ElectricService {

    @Resource
    private ElectricService electricService;

    @Resource
    private AdminUserService adminUserService;


    public void charge() throws Exception {
//        System.out.println("Electric charging ...");
        // this 引用到的只是一个普通对象    代理对象（spring中能获取到的对象）才具有AOP功能
//        this.pay();
        // 正解 1
        //  @Resource   private ElectricService electricService;

        // 正解2 获取代理对象
//        @EnableAspectJAutoProxy 里加一个配置项 exposeProxy = true，表示将代理对象放入到 ThreadLocal，这样才可以直接通过 AopContext.currentProxy() 的方式获取到
//        ElectricService electric = ((ElectricService) AopContext.currentProxy());

//        electricService.pay();
        electricService.doCharge();
    }

//    public void pay() throws Exception {
//        System.out.println("Pay with alipay ...");
//        Thread.sleep(1000);
//    }

    public void doCharge() {
        System.out.println("Electric charging ...");
    }


    public void pay() throws Exception {
        adminUserService.login();
        String payNum = adminUserService.getAdminUser().getPayNum();
        System.out.println("User pay num : " + payNum);
        System.out.println("Pay with alipay ...");
        Thread.sleep(1000);
    }
}