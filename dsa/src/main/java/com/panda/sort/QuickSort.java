package com.panda.sort;

import java.util.Arrays;

/**
 * @author: ljj
 * @date: 2022/6/9
 * @description:
 */
public class QuickSort {
    public static void main(String[] args) {
        int[] arr = {65461, 34, 868, 4, 5, 2, 3, 99, 5345,3, 34, 2,6564, 434};
        System.out.println(Arrays.toString(arr));
        new QuickSort().quickSort(arr,0,arr.length-1);
        System.out.println(Arrays.toString(arr));
    }

    public void quickSort(int[] arr,int left,int right) {
        // 递归边界
        if (left < right) {
            // 挑选基准值索引，已就位元素
            int pivotIndex = partition(arr, left, right);
            // 分割 递归排序子序列
            quickSort(arr,left,pivotIndex-1);
            quickSort(arr,pivotIndex+1,right);
        }
    }

    private int partition(int[] arr, int left, int right) {
        // 基准点
        int pivotValue = arr[left];
        while (left < right) {
            // 大于等于基准值的都位于其右边   等于的位于那边都可以
            while (left < right && pivotValue <= arr[right]) {
                right--;
            }
            if (left < right) {
                arr[left] = arr[right];
            }
            // 小于等于基准值的都位于其右边
            while (left < right && arr[left] <= pivotValue) {
                left++;
            }
            if (left < right) {
                arr[right] = arr[left];
            }
        }
        // 基准值就位
        arr[left] = pivotValue;
        // 返回基准值索引
        return left;
    }
}
