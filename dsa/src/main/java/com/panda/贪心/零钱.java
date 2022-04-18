package com.panda.贪心;

/**
 * @author: ljj
 * @date: 2022/4/13
 * @description:
 */
public class 零钱 {
    static int[] coinArray = {10, 5, 2, 1};
    static int[] coinArray2 = {10, 7, 1};


    public static void main(String[] args) {
        solution(coinArray, 15);
        System.out.println("------------------");
        solution(coinArray2, 15);
    }


    static void solution(int[] coinArray, int number) {
        for (int coin : coinArray) {
            if (number >= coin) {
                int count = number / coin;
                number = number % coin;
                System.out.println(count + "张：" + coin);
            }
        }
    }
}
