package com.panda.双指针;

/**
 * @ClassName: 只出现一次的数字.java
 * @Author: jjl
 * @Description: 给定一个非空整数数组，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。
 * @Date: 2021-07-26
 * @Version: 1.0
 */
public class 只出现一次的数字 {
    public int singleNumber(int[] nums) {


        int result = 0;

        for (int i : nums) {
            result ^= i;
        }

        return result;
    }
}
