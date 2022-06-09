package com.panda.sort;

import java.util.Arrays;

/**
 * @author: ljj
 * @date: 2022/6/8
 * @description:
 */
public class InsertSort {

    public static void main(String[] args) {
        int[] arr = {65461, 34, 868, 4, 5, 2, 3, 99, 5345,3, 34, 2,6564, 434};
        System.out.println(Arrays.toString(arr));
        insertSort(arr);
        System.out.println(Arrays.toString(arr));
    }


    public static void insertSort(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            // 新元素  默认第一个元素未有序元素
            int key = array[i + 1];
            // 有序数组长度
            int j = i;
            // 取出下一个元素，在已经排序的元素序列中从后向前扫描,如果已排序元素大于新元素
            while (j >= 0 && array[j] > key) {
                // 将已排序元素移到下一位置
                array[j + 1] = array[j];
                j--;
            }
            // 找到已排序的元素小于或者等于新元素的位置,将新元素插入到该位置后
            array[j + 1] = key;
        }
    }



}
