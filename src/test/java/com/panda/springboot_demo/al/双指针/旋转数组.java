package com.panda.springboot_demo.al.双指针;

/**
 * @ClassName: 旋转数组.java
 * @Author: jjl
 * @Description: 给定一个数组，将数组中的元素向右移动 k 个位置，其中 k 是非负数
 * @Date: 2021-07-25
 * @Version: 1.0
 */
public class 旋转数组 {

    public void rotate(int[] nums, int k) {
        if (nums == null || nums.length < 2) {
            return;
        }

        int length = nums.length;

        // 复制一个临时数组
        int[] temp = new int[length];
        for (int i = 0; i < length; i++) {
            temp[i] = nums[i];
        }

        // 移动向右k
        for (int i = 0; i < length; i++) {
            nums[(i + k) % length] = temp[i];
        }

    }
}
