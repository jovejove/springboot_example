package com.panda.al.stack;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @ClassName: 整数反转.java
 * @Author: jjl
 * @Description:
 * @Date: 2021-07-26
 * @Version: 1.0
 */
public class 整数反转 {

    public int reverse(int x) {

        String str = "";
        int flag = x > 0 ? 1 : -1;


        String text = Math.abs(x) + "";
        ExecutorService executorService = Executors.newCachedThreadPool();
        ExecutorService executorService2 = Executors.newFixedThreadPool(10);
        ExecutorService executorService3 = Executors.newSingleThreadExecutor();


        return flag * Integer.parseInt(str);


    }
}
