package com.panda.双指针;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @ClassName: 两个数组的交集2.java
 * @Author: jjl
 * @Description:
 * @Date: 2021-07-26
 * @Version: 1.0
 */
public class 两个数组的交集2 {
    public int[] intersect(int[] nums1, int[] nums2) {
        /**
         * 1.先排序
         * 2.遍历两个数组
         */

        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int i = 0, j = 0;

        List<Integer> list = new LinkedList<>();

        while (i < nums1.length && j < nums2.length) {
            if (nums1[i] < nums2[j]) {
                i++;
            } else if (nums1[i] > nums2[j]) {
                j++;
            } else {
                list.add(nums1[i]);
                i++;
                j++;
            }
        }

        int[] res = new int[list.size()];

        for (int k = 0; k < list.size(); k++) {
            res[k] = list.get(k);
        }

        return res;

    }
}
