package com.panda.design.Singleton;

/**
 * @ClassName: SingletonEnum.java
 * @Author: jjl
 * @Description:
 * @Date: 2021-07-23
 * @Version: 1.0
 */

public enum SingletonEnum {
    // 这种实现方式还没有被广泛采用，但这是实现单例模式的最佳方法。它更简洁，自动支持序列化机制，绝对防止多次实例化。
    // 这种方式是 Effective Java 作者 Josh Bloch 提倡的方式，它不仅能避免多线程同步问题，而且还自动支持序列化机制，防止反序列化重新创建新的对象，绝对防止多次实例化
    INSTANCE;

    public void whateverMethod() {
    }
}
