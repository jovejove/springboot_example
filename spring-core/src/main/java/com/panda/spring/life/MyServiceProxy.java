package com.panda.spring.life;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author: ljj
 * @date: 2022/5/17
 * @description:
 */
public class MyServiceProxy implements InvocationHandler {

    private MyService myService;

    public MyServiceProxy(MyService myService) {
        this.myService = myService;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

//        if (method.getName().equals("fuck")) {
//            //前置处理
//            System.out.println("我有腹肌");
//
//            //调用被代理的方法 who
//            Object result = method.invoke(myService, args);
//
//            //后置处理
//            System.out.println("我最帅");
//
//            return result;
//        }
//        System.out.println("我有腹肌");
        Object object = method.invoke(myService, args);
//        System.out.println("我最帅");
        return object;
    }

    public MyService getProxy() {
        return (MyService) Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(), myService.getClass().getInterfaces(), this);
    }
}
