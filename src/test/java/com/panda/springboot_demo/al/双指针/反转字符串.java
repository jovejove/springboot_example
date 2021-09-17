package com.panda.springboot_demo.al.双指针;

/**
 * @ClassName: 反转字符串.java
 * @Author: jjl
 * @Description:
 * @Date: 2021-07-26
 * @Version: 1.0
 */
public class 反转字符串 {
    public void reverseString(char[] s) {

        int max = s.length - 1;

        int i = 0;
        while (i < max) {
            char temp = s[i];
            s[i++] = s[max];
            s[max--] = temp;
        }

    }
}
