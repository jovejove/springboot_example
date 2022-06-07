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
        List<Integer> list = Arrays.asList(1, 9, 94, 6, 2, 2, 5, 7, 8, 3, 2);
        System.out.println(list);
        new BubbleSort().bubbleSortV2(list);
        System.out.println(list);
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
}
