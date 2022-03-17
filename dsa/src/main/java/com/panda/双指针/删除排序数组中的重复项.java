package com.panda.双指针;

/**
 * @ClassName: 删除排序数组中的重复项.java
 * @Author: jjl
 * @Description:
 * @Date: 2021-07-25
 * @Version: 1.0
 */
public class 删除排序数组中的重复项 {

    public int removeDuplicates(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int l = 0;

        for (int r = 1; r < nums.length; r++) {

            // 如果相邻不等于
            if (nums[r] != nums[l]) {
                nums[++l] = nums[r];
            }
        }
        return l;
    }
}
