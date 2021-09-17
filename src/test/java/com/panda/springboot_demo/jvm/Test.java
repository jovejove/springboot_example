package com.panda.springboot_demo.jvm;

/**
 * @ClassName: Test.java
 * @Author: jjl
 * @Description:
 * @Date: 2021-07-08
 * @Version: 1.0
 */
public class Test {

    public static void main(String[] args) {
        //-Xmx20m -Xms5m  最大堆内存为20M 初始堆内存为5M
        //在实际工作中，我们可以直接将初始的堆大小与最大堆大小相等，这样的好处是可以减少程序运行时垃圾回收次数，从而提高效率
        System.out.println("最大堆大小【-Xmx参数】:" + Runtime.getRuntime().maxMemory() / 1024.0 / 1024 + "M");
        System.out.println("初始堆大小【-Xms参数】:" + Runtime.getRuntime().totalMemory() / 1024.0 / 1024 + "M");
        System.out.println("可用内存【无参数】:" + Runtime.getRuntime().freeMemory() / 1024.0 / 1024 + "M");
    }
}
