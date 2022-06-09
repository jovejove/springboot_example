package com.panda.sort;

import java.util.Arrays;

/**
 * @author: ljj
 * @date: 2022/6/9
 * @description:
 */
public class SelectSort {

    public static void main(String[] args) {
        int[] arr = {65461, 34, 868, 4, 5, 2, 3, 99, 5345, 34, 6564, 434};
        System.out.println(Arrays.toString(arr));
        selectSort(arr);
        System.out.println(Arrays.toString(arr));

    }

    public static void selectSort(int[] arr) {
        // 有序段的索引
        int minIndex;
        for (int i = 0; i < arr.length; i++) {
             minIndex = i;
            //遍历找出未排序中的元素中最小值下标  注：arr.length
            for (int j = i; j < arr.length; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }
            // 若最小值下标与未排序中最左侧下标不一致则交换
            if (minIndex != i) {
                int temp = arr[i];
                arr[i] = arr[minIndex];
                arr[minIndex] = temp;
            }
        }
    }


}
