package com.panda.design.behavior.template;//取钱业务的实现类

import java.util.ArrayList;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * @author ljj
 */
public class DrawMoneyHandler extends AbstractBusinessHandler {
    @Override
    public void handle() {
        System.out.println("draw 1000");


    }

    public static void main(String[] args) {
        DrawMoneyHandler drawMoneyHandler = new DrawMoneyHandler();
        drawMoneyHandler.execute();

        // 供给型函数式接口
        Supplier<ArrayList> runnable = ArrayList::new;

        // 消费性函数式接口
        Consumer<String> stringConsumer = (s) -> System.out.println(s.length());
        Stream.of("ab", "abc", "a", "abcd").forEach(stringConsumer);
    }
}