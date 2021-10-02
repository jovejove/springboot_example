package com.panda.贪心;

import java.util.Arrays;

/**
 * @Author: jjl
 * @Description:
 * @Date: 2021-09-30
 * @Version: 1.0
 */
public class BubbleSort {

    public static void main(String[] args) {

        int[] arr = {9999, 10, 2, 3, 58, 34, 654, 23, 2, 45, 67, 8};

//        bubbleSort(arr, 0, arr.length);
        bubbleSortImprove(arr, 0, arr.length);

        System.out.println(Arrays.toString(arr));

    }

    private static void bubbleSort(int[] arr, int lo, int hi) {
        while (lo < hi) {
            // 数据最后一个有序
            bubble(arr, lo, hi--);
        }
    }

    private static void bubble(int[] arr, int lo, int hi) {
        while (++lo < hi) {
            // 贪心算法比较 若逆序
            if (arr[lo - 1] > arr[lo]) {
                // 则交换 交换到一个最大值在数组尾部
                swap(arr, lo - 1, lo);
            }
        }
    }

    private static void bubbleSortImprove(int[] arr, int lo, int hi) {
        // 逐躺扫描交换，直至全序
        while (lo < (hi = bubbleImprove(arr, lo, hi))) {
        }
    }

    private static int bubbleImprove(int[] arr, int lo, int hi) {
        int last = lo;
        // 自左向右，注意检查各队相邻元素
        while (++lo < hi) {
            // 贪心算法比较 逆序交换 交换到一个最大值在数组尾部
            if (arr[lo - 1] > arr[lo]) {
                // 更新最右逆序对位置记录
                last = lo;
                // 并交换
                swap(arr, lo - 1, lo);
            }
        }
        // 逆序对均在last的左边
        return last;
    }

    // 交换
    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
