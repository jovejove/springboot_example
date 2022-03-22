package com.example.java.jvm;

/**
 * @author: ljj
 * @date: 2022/3/22 11:22
 * @description:
 */
public class StackSOF {
    private static int stackDeep = 0;

    public static void main(String[] args) {
        stackLeak();
    }

    public static void stackLeak() {
        stackDeep++;
        try {
            stackLeak();
        } catch (StackOverflowError e) {
            System.out.println("stack Length:" +stackDeep);
            e.printStackTrace();
        }
    }
}
