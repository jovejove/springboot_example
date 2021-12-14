package com.panda.design.service;

/**
 * 策略模式
 *
 * @author Administrator
 */
public interface Strategy {
    /** 操作
     * @param num1
     * @param num2
     * @return 操作值
     */
    int doOperation(int num1, int num2);
}

