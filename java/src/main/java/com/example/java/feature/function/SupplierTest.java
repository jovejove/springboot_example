package com.example.java.feature.function;

import java.util.function.Supplier;

/**
 * @author: ljj
 * @date: 2022/6/7
 * @description:
 */
public class SupplierTest {

    private static void test(Supplier<Integer> supplier) {
        System.out.println(supplier.get());
    }

    private static void test(Integer number) {
        System.out.println(number);
    }


    public static void main(String[] args) {
        SupplierTest.test(()->1);
    }
}
