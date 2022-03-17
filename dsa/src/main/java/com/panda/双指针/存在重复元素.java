package com.panda.双指针;

import java.util.Arrays;

/**
 * @ClassName: 存在重复元素.java
 * @Author: jjl
 * @Description: 给定一个整数数组，判断是否存在重复元素。
 * <p>
 * 如果存在一值在数组中出现至少两次，函数返回 true 。如果数组中每个元素都不相同，则返回 false 。
 * @Date: 2021-07-26
 * @Version: 1.0
 */
public class 存在重复元素 {

    public boolean containsDuplicate(int[] nums) {
        if (nums == null || nums.length < 2) {
            return false;
        }

        Arrays.sort(nums);

        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] == nums[i + 1]) {
                return true;
            }
        }

        return false;

    }
}
