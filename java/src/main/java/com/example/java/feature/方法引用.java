package com.example.java.feature;

import java.util.ArrayList;
import java.util.List;

/**
 * 方法引用通过方法的名字来指向一个方法。
 * 方法引用可以使语言的构造更紧凑简洁，减少冗余代码。
 * 方法引用使用一对冒号 :: 。
 */
public class 方法引用 {
    public static void main(String args[]){
        List<String> names = new ArrayList();
        names.add("Google");
        names.add("Runoob");
        names.add("Taobao");
        names.add("Baidu");
        names.add("Sina");
        names.forEach(System.out::println);
        System.out.println();
    }
}
