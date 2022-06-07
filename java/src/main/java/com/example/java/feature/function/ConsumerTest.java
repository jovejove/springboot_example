package com.example.java.feature.function;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.function.Consumer;

/**
 * @author: ljj
 * @date: 2022/6/7
 * @description:
 */

@Data
@AllArgsConstructor
public class ConsumerTest {

    private int x;

    private void test(Consumer<String> consumer,String type) {
        System.out.println(11);
        consumer.accept(type);
        System.out.println(22);
    }

    public static void main(String[] args) {
        new ConsumerTest(3).test((type)->{
            if ("test".equals(type)) {
                System.out.println(type);
            }else {
                System.out.println("prd");
            }
        },"prd2");
    }
}
