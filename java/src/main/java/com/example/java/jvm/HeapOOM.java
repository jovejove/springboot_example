package com.example.java.jvm;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: ljj
 * @date: 2022/3/22 10:48
 * @description: 内存溢出测试
 * vm args: -Xms20m -Xmx20m -XX:+HeapDumpOnOutOfMemoryError  -XX:HeapDumpPath=D:\workspace\springboot_example
 */
public class HeapOOM {

    private byte[] bytes = new byte[1024];


    public static void main(String[] args) {

        List<HeapOOM> list = new ArrayList<>();
        while (true) {
            list.add(new HeapOOM());
        }
    }
}
