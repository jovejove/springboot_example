package com.panda.jvm;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: ljj
 * @date: 2022/3/22 10:48
 * @description:
 */
public class HeapOOM {
    public static void main(String[] args) {
        List<HeapOOM> list = new ArrayList<>();
        while (true) {
            list.add(new HeapOOM());
        }
    }
}
