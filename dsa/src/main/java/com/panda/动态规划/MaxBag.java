package com.panda.动态规划;

import com.alibaba.fastjson.JSON;

/**
 * @author: ljj
 * @date: 2022/4/14
 * @description: 01背包问题
 */
public class MaxBag {
    /**
     * 物品个数 行
     */
    static int n = 3;
    /**
     * 背包容量 列
     */
    static int c = 4;
    /**
     * 物品价值数组
     */
    static int[] value = new int[]{1500, 3000, 2000};
    /**
     * 对应物品价值的重量
     */
    static int[] weight = new int[]{1, 4, 3};

    /**
     * 构建表格
     */
    static int[][] initValue = new int[n][c];


    public static void main(String[] args) {
        int maxValue = MaxBag.maxValue(n, c, value, weight, initValue);
        printBag(n, c, initValue);
//        System.out.println(maxValue);

    }

    private static int maxValue(int x, int y, int[] value, int[] weight, int[][] initValue) {
        // 物品行
        for (int i = 0; i < x; i++) {
            // 容量列
            for (int j = 1; j <= y; j++) {
                int lastValue;
                if (i == 0) {
                    initValue[i][j - 1] = weight[i] <= j ? value[i] : 0;
                } else {
                    // 上一个物品的价值
                    lastValue = initValue[i - 1][j - 1];
                    // 若物品重量<=当前行列重量，则当前最大价值 = 当前容量物品的价值 + 剩余容量物品的价值，反之当前行列为上一个物品的价值
                    int currentValue = weight[i] <= j ? (j > weight[i] ? value[i] + initValue[i - 1][j - weight[i]] : value[i]) : lastValue;
                    // 取currentValue和lastValue较大者
                    initValue[i][j - 1] = Math.max(lastValue, currentValue);
                }
            }
        }
        return initValue[x - 1][y - 1];
    }

    static void printBag(int x, int y, int[][] initValue) {
        for (int i = 0; i < x; i++) {
            for (int j = 0; j < y; j++) {
                System.out.printf("%8d", initValue[i][j]);
            }
            System.out.println();
        }
    }
}
