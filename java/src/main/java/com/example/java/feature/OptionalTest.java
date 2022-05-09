package com.example.java.feature;

import java.util.Optional;

/**
 * Optional 类是一个可以为null的容器对象。如果值存在则isPresent()方法会返回true，调用get()方法会返回该对象
 * 解决空指针异常
 *
 * @author Administrator
 */
public class OptionalTest {
    public static void main(String args[]) {

        String nullVal = null;
        String nonNullVal = "june";

        Optional<Object> optional = Optional.ofNullable(nullVal);
        Optional<Object> optional2 = Optional.ofNullable(nonNullVal);

        // 可写链式调用
        nullVal = (String) optional.orElse("jun");
        System.out.println(nullVal);

        System.out.println(optional2);
        // 返回映射对象
        Optional<String> optionalEe = optional2.flatMap(s -> Optional.of("junee"));
        System.out.println(optionalEe);

        optional2.ifPresent(System.out::println);
        // 返回映射值
        Optional<String> stringOptional = optional2.map(s -> "map lab:" + s);
        stringOptional.ifPresent(System.out::println);

    }

    public static void test1() {
        OptionalTest optionalTest = new OptionalTest();
        Integer value1 = null;
        Integer value2 = new Integer(10);

        // Optional.ofNullable - 允许传递为 null 参数
        Optional<Integer> a = Optional.ofNullable(value1);

        // Optional.of - 如果传递的参数是 null，抛出异常 NullPointerException
        Optional<Integer> b = Optional.of(value2);
        System.out.println(optionalTest.sum(a, b));
    }

    public Integer sum(Optional<Integer> a, Optional<Integer> b) {

        // Optional.isPresent - 判断值是否存在

        System.out.println("第一个参数值存在: " + a.isPresent());
        System.out.println("第二个参数值存在: " + b.isPresent());

        // Optional.orElse - 如果值存在，返回它，否则返回默认值
        Integer value1 = a.orElse(new Integer(0));

        //Optional.get - 获取值，值需要存在
        Integer value2 = b.get();
        return value1 + value2;
    }
}
