package com.example.java.concurrent;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;


/**
 *
 */
public class AtomicExample {

    private final AtomicInteger atomicIntegerCnt = new AtomicInteger();
    private int intCnt = 0;

    public int getAtomicIntegerCnt() {
        return atomicIntegerCnt.get();
    }

    public void addAtomicIntegerCnt() throws InterruptedException {
        Thread.sleep(50);
        this.atomicIntegerCnt.incrementAndGet();
    }

    public int getIntCnt() {
        return intCnt;
    }

    public void addIntCnt() throws InterruptedException {
        Thread.sleep(50);
//        synchronized (this) {
        this.intCnt++;
//        }
    }

    public static void main(String[] args) throws InterruptedException {
        int threadSize = 1000;
        AtomicExample example = new AtomicExample();
        CountDownLatch countDownLatch = new CountDownLatch(threadSize);
        // ExecutorService threadPool = Executors.newCachedThreadPool();不建议使用，有可能内存溢出。
        // 原因：maximum=Integer.MAX_VALUE=2<sup>31</sup>-1=0x7fffffff SynchronousQueue是无界队列（链表实现）
        // 7个参数
        ThreadPoolExecutor threadPool = new ThreadPoolExecutor(50, 100, 30, TimeUnit.MINUTES
                , new ArrayBlockingQueue<Runnable>(100), Executors.defaultThreadFactory(), new ThreadPoolExecutor.CallerRunsPolicy());


        for (int i = 0; i < threadSize; i++) {
            threadPool.execute(() -> {
                try {
                    example.addAtomicIntegerCnt();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                try {
                    example.addIntCnt();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        threadPool.shutdown();
        System.out.println("AtomicInteger:" + example.getAtomicIntegerCnt());
        System.out.println("IntegerCnt:" + example.getIntCnt());
    }
}
