package com.panda.sort;

import java.util.Arrays;
import java.util.List;

/**
 * @author: ljj
 * @date: 2022/6/7
 * @description:
 */
public class BubbleSort {

    public static void main(String[] args) {
//        List<Integer> list = Arrays.asList(1, 9, 94, 6, 2, 2, 5, 7, 8, 3, 2);
//        System.out.println(list);
//        new BubbleSort().bubbleSortV2(list);
//        System.out.println(list);

        int[] arr = {65461, 34, 868, 4, 5, 2, 3, 99, 5345,3, 34, 2,6564, 434};
        System.out.println(Arrays.toString(arr));
        new BubbleSort().bubbleSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    public void bubbleSort(List<Integer> list) {
        int length = list.size();
        for (int i = 0; i < length - 1; i++) {
            for (int j = 0; j < length - 1 - i; j++) {
                if (list.get(j) > list.get(j + 1)) {
                    swap(list, j, j+1);
                }
            }
        }
    }


    public void bubbleSortV2(List<Integer> list) {
        int length = list.size();
        for (int i = 0; i < length - 1; i++) {
            // 是否发生交换。没有交换，提前跳出外层循环
            boolean flag = false;
            for (int j = 0; j < length - 1 - i; j++) {
                if (list.get(j) > list.get(j + 1)) {
                    swap(list, j, j+1);
                    flag = true;
                }
            }
            if (!flag) {
                break;
            }
        }
    }


    void swap(List<Integer> list,Integer a,Integer b) {
        int temp = list.get(a);
        list.set(a, list.get(b));
        list.set(b, temp);
    }


    void swap(int[] arr,Integer a,Integer b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }

    public void bubbleSort(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            // 是否发生交换 没有交换，提前跳出外层循环
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    swap(arr,j,j+1);
                }
            }
        }
    }
    public void bubbleSortV2(int[] arr) {

        for (int i = 0; i < arr.length - 1; i++) {
            // 是否发生交换 没有交换，提前跳出外层循环
            boolean flag = false;
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    swap(arr,j,j+1);
                    flag = true;
                }
            }
            if (!flag) {
                return;
            }
        }
    }
}
