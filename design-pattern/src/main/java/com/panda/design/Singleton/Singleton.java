package com.panda.design.Singleton;

/**
 * @ClassName: Singleton.java
 * @Author: jjl
 * @Description:
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
