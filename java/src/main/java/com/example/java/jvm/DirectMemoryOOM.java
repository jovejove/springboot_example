package com.example.java.jvm;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * @author: ljj
 * @date: 2022/3/22 16:58
 * @description:
 * 直接内存溢出
 */
public class DirectMemoryOOM {
    private static final int _1MB = 1024 * 1024;

    public static void main(String[] args) throws IllegalAccessException {
        Field unsafeField = Unsafe.class.getDeclaredFields()[0];
        unsafeField.setAccessible(true);
        Unsafe unsafe = (Unsafe) unsafeField.get(null);
        while (true) {
            unsafe.allocateMemory(_1MB);
        }
    }
}
