package com.example.java.concurrent;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: ljj
 * @date: 2022/3/22 10:35
 * @description:
 */
public class MultiTask {
    public static void main(String[] args) throws InterruptedException {
        byte[] bytes = new byte[1024];
        List<Byte> byteList = new ArrayList<>();
        while (true) {
            byteList.add((byte) 1024);
            Thread.sleep(1000);
            System.out.println(byteList.size());
        }
    }
}
