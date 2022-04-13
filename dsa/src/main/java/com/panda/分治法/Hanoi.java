package com.panda.分治法;

/**
 * @author: ljj
 * @date: 2022/4/13
 * @description:
 */
public class Hanoi {
    private static int step;

    public static void main(String[] args) {
        Hanoi(2, 'a', 'b', 'c');
    }

    /**
     * 将n个盘子从a借助b移动到c
     * @param n
     * @param a
     * @param b
     * @param c
     */
    static void Hanoi(int n, char a, char b, char c) {
        if (n > 1) {
            // 先将前n-1个盘子从a借助c移动到b
            Hanoi(n-1,a,c,b);
            // 将第n个盘子从a直接移动到c
            move(n,a,c);
            // 再将前n-1个盘子从b借助a移动到c
            Hanoi(n - 1, b, a, c);
        }else {
            // 只有一个盘子时，直接从a移动到c。递归出口
            move(n,a,c);
        }
    }

    static void move(int n, char a, char c) {
        System.out.printf("第%s步，将第%s个盘子从%s移动到%s \n",++step,n,a,c);
    }
}
