package com.panda.贪心;

import java.util.Arrays;

/**
 * @ClassName: GnomeSort.java
 * @Author: jjl
 * @Description:
 * @Date: 2021-09-30
 * @Version: 1.0
 */
public class GnomeSort {

    public static void main(String[] args) {

        int[] arr = {10, 2, 3, 58, 34, 654, 23, 2, 45, 67, 8};

        gnomeSort(arr);

        System.out.println(Arrays.toString(arr));

    }

    private static void gnomeSort(int[] arr) {
        for (int i = 1; i < arr.length; ) {
            if (i < 1 || arr[i - 1] <= arr[i]) {
                // 顺序向后执行
                i++;
            } else {
                // 逆序交换
                swap(arr, i - 1, i);
                // 向前检查是否有逆序对
                i--;
            }
        }
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}


