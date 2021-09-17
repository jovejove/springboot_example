package com.panda.springboot_demo.al.贪心;

import java.util.Arrays;

/**
 * @ClassName: 饼干.java
 * @Author: jjl
 * @Description:
 * @Date: 2021-07-24
 * @Version: 1.0
 */
public class 饼干 {

    public int findContentChildren(int[] g, int[] s) {

        // 排序
        Arrays.sort(g);
        Arrays.sort(s);

        // 局部最优
        int gi = 0, si = 0;
        while (gi < g.length && si < s.length) {

            // 局部最优 最小的饥饿值<=最小的饼干
            if (g[gi] <= s[si]) {
                gi++;
            }
            si++;
        }

        return gi;
    }
}
