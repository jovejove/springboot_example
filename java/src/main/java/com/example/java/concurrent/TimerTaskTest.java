package com.example.java.concurrent;

import org.springframework.util.StopWatch;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author: ljj
 * @date: 2022/3/22 9:48
 * @description: 定时任务
 */
public class TimerTaskTest {

    public static void main(String[] args) {
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                System.out.println("hello timer task");
            }
        };

        Timer timer = new Timer("定时任务测试",false);

        System.out.println("10秒后执行");
        timer.schedule(timerTask,10000);

        System.out.println("每隔10s  10s中的第五秒执行");
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("hello timer task 5000 10000");
            }
        }, 5000, 10000);

        ScheduledThreadPoolExecutor scheduledThreadPoolExecutor = new ScheduledThreadPoolExecutor(10);
        scheduledThreadPoolExecutor.schedule(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("活动线程数："+scheduledThreadPoolExecutor.getActiveCount());
            System.out.println("ScheduledThreadPoolExecutor多线程定时任务调度测试");
        }, 3, TimeUnit.SECONDS);
        scheduledThreadPoolExecutor.shutdown();
    }
}
