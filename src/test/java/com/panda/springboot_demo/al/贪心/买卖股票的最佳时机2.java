package com.panda.springboot_demo.al.贪心;

/**
 * @ClassName: 买卖股票的最佳时机2.java
 * @Author: jjl
 * @Description:
 * @Date: 2021-07-25
 * @Version: 1.0
 */
public class 买卖股票的最佳时机2 {
    public int maxProfit(int[] prices) {
        if (prices == null || prices.length < 2) {
            return 0;
        }

        int sum = 0;

        for (int i = 0; i < prices.length - 1; i++) {

            sum += Math.max(prices[i + 1] - prices[i], 0);
        }

        return sum;
    }
}
