package com.example.java.feature;

import org.springframework.util.Assert;

import java.sql.SQLOutput;
import java.util.Objects;
import java.util.Optional;

/**
 * @author: ljj
 * @date: 2022/5/9
 * @description:
 */

public class ObjectsTest {
    public static void main(String[] args) {
        objectTest();

    }


    public static void objectTest() {
        String[] aArray = new String[]{"a", "b"};
        String[] bArray = new String[]{"a", "b"};
        String[] cArray = new String[]{"b", "a"};
        String strBlank = "";

        System.out.println(Objects.equals(null,null));
        System.out.println(Objects.equals(aArray,bArray));
        System.out.println(Objects.deepEquals(aArray,bArray));
        System.out.println(Objects.deepEquals(aArray,cArray));

        try {
            Assert.notNull(null);

        } catch (Exception e) {
            e.printStackTrace();
        }
        Assert.notNull(null,()->"Assert不能为空呀function interface");
        Assert.notNull(null,"Assert不能为空呀");
        Objects.requireNonNull(null);
        Objects.requireNonNull(null,"不能为空呀");
    }
}
