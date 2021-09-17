package com.panda.springboot_demo.pattern.strategy;

/**
 * @ClassName: OperationAdd.java
 * @Author: jjl
 * @Description:
 * @Date: 2021-07-23
 * @Version: 1.0
 */
public class OperationSubtract implements Strategy {
    @Override
    public int doOperation(int num1, int num2) {
        return num1 * num2;
    }
}
