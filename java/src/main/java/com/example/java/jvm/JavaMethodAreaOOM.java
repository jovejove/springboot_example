package com.example.java.jvm;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;

/**
 * @author: ljj
 * @date: 2022/3/22 11:44
 * @description: 方法区溢出测试.
 * jvm < 1.8 VM Args: -XX:PermSize=10k -XX:MaxPermSize=10k
 * jvm >= 1.8VM Args: -XX:MetaspaceSize=10m -XX:MaxMetaspaceSize=10m
 */
public class JavaMethodAreaOOM {
    public static void main(String[] args) {
        while (true) {
            Enhancer enhancer = new Enhancer();
            enhancer.setSuperclass(OOMObject.class);
            enhancer.setUseCache(false);
            enhancer.setCallback((MethodInterceptor) (o, method, objects, methodProxy) -> methodProxy.invokeSuper(o, args));
        }
    }

    static class OOMObject {

    }
}


