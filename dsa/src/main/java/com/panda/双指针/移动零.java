package com.panda.双指针;

/**
 * @ClassName: 移动零.java
 * @Author: jjl
 * @Description: 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
 * @Date: 2021-07-26
 * @Version: 1.0
 */
public class 移动零 {
    public void moveZeroes(int[] nums) {

        int len = nums.length;
        int i = 0;
//        for (int j = 0; j < len; j++) {
//            if (nums[j] != 0) {
//                nums[i++] = nums[j];
//            }
//        }
//
//        while (i < nums.length) {
//            nums[i++] = 0;
//        }


//        for (int j = 0; j < len; j++) {
//            if (nums[j] == 0) {
//                i++;
//            }else if (i != 0) {
//                nums[j-i] = nums[j];
//                nums[j] = 0;
//            }
//        }


        // 不为零的往前走
//        for (int j = 0; j < len; j++) {
//            if (nums[j] != 0) {
//                int temp = nums[i];
//                nums[i] = nums[j];
//                nums[j] = temp;
//                i++;
//            }
//        }


    }


}
