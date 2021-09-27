package com.panda.al.双指针;

/**
 * @ClassName: 加一.java
 * @Author: jjl
 * @Description:
 * @Date: 2021-07-26
 * @Version: 1.0
 */
public class 加一 {

    public int[] plusOne(int[] digits) {

        int len = digits.length;

        for (int i = len - 1; i >= 0; i--) {

            if (digits[i] != 9) {
                ++digits[i];
                return digits;
            } else {
                digits[i] = 0;
            }
        }

        int[] arr = new int[len + 1];

        arr[0] = 1;

        return arr;

    }
}
