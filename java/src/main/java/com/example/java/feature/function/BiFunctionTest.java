package com.example.java.feature.function;

import java.util.ArrayList;
import java.util.function.BiFunction;

/**
 * @author: ljj
 * @date: 2022/6/7
 * @description:
 */
public class BiFunctionTest {

    private int x;
    private int y;

    public BiFunctionTest(int x, int y) {
        this.x = x;
        this.y = y;
    }

    private Object test(BiFunction<Integer, Integer, Integer> biFunction) {
        return biFunction.apply(this.x, this.y);
    }


    public static void main(String[] args) {
        Object test = new BiFunctionTest(2, 3).test((a, b) -> {
            System.out.println(a);
            System.out.println(b);
            return a + b;
        });

        System.out.println(test);

    }


}
