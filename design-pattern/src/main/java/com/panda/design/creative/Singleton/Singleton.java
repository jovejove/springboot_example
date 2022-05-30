package com.panda.design.creative.Singleton;

/**
 * @ClassName: Singleton.java
 * @Author: jjl
 * @Description: 双重检查锁  意图：保证一个类仅有一个实例，并提供一个访问它的全局访问点。
 * @Date: 2021-07-23
 * @Version: 1.0
 */
public class Singleton {

    private volatile static Singleton singleton;

    private Singleton() {
    }

    public static Singleton getInstance() {
        if (singleton == null) {
            synchronized (Singleton.class) {
                if (singleton == null) {
                    singleton = new Singleton();
                }
            }
        }
        return singleton;
    }
}
