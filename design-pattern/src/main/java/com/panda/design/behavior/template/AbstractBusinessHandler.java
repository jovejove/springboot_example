package com.panda.design.behavior.template;

import org.apache.commons.lang.math.RandomUtils;

/**
 * 模板方法设计模式的抽象类
 * @author ljj
 */
public abstract class AbstractBusinessHandler {

    /**
     * 模板方法
     */
    public final void execute() {
        getNumber();
        handle();
        judge();
    }

    /**
     * 取号
     * @return
     */
    private void getNumber() {

        System.out.println("number-00" + RandomUtils.nextInt());

    }

    /**
     * 办理业务
     */
    public abstract void handle(); //抽象的办理业务方法，由子类实现

    /**
     * 评价
     */
    private void judge() {
        System.out.println("give a praised");
    }

}