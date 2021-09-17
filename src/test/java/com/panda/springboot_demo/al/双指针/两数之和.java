package com.panda.springboot_demo.al.双指针;

import java.util.Arrays;

/**
 * @ClassName: liang.java
 * @Author: jjl
 * @Description:
 * @Date: 2021-07-23
 * @Version: 1.0
 */
public class 两数之和 {

    public static int[] twoSum(int[] nums, int target) {

//        Map<Integer, Integer> map = new HashMap<>();
//
//        for (int j = 0; j < nums.length; j++) {
//
//            if (map.get(target - nums[j]) != null) {
//                return new int[]{map.get(target - nums[j]), j};
//            }
//
//            map.put(nums[j], j);
//        }

        int i = 0, j = 1;
        int max = nums.length - 1;
        while (target != nums[i] + nums[j]) {

            // 边界
            if (j == max) {
                // 下一轮开始
                i++;
                // 重置
                j = i;
            }
            j++;
        }

        return new int[]{i, j};

    }

    public static void main(String[] args) {
        int[] nums = new int[]{3, 2, 4};
        System.out.println(Arrays.toString(twoSum(nums, 6)));

    }

}
