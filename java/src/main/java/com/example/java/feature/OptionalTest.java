package com.example.java.feature;

import com.example.java.User;
import org.springframework.util.Assert;

import java.util.Optional;

/**
 * Optional 类是一个可以为null的容器对象。
 * 虽然链式编程代码优雅，但是代码可读性降低
 *
 * @author Administrator
 */
public class OptionalTest {
    public static void main(String args[]) throws Exception {
        User user = null;

        // if null
//        Optional.ofNullable(user).orElseThrow(() -> new Exception("user not exists"));
        Assert.notNull(user,"参数异常");

        // if null give a default value
        user = Optional.ofNullable(user).orElse(createUser());
        user = Optional.ofNullable(user).orElseGet(OptionalTest::createUser);

        // instance not null
        Optional.ofNullable(user).ifPresent(OptionalTest::doSomething);

        // multi null
        System.out.println(getCity(user));

        // if else
        user = Optional.ofNullable(user).filter(u -> "cat".equals(u.getName())).orElseGet(() -> new User(2L, "cat", 18, null));
        System.out.println(user);
    }

    static String getCity(User user) throws Exception {
        return Optional.ofNullable(user).map(User::getAddress).map(User.Address::getCity).orElseThrow(() -> new Exception("取值错误"));
    }

    public static User createUser() {
        User user = new User();
        user.setName("panda");
        user.setAddress(new User.Address("gz"));
        return user;
    }

    static void doSomething(User user) {
        System.out.println(user);
    }

    static void test2() {
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
