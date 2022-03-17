package com.example.java.concurrent;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class Example{
    private int count = 0;

    private AtomicInteger atomicInteger = new AtomicInteger(0);

    public Example(String name) {
    }

    public void run() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("count: " + count++);
//        System.out.println("name: " + this.getName() + ", count: " + atomicInteger.getAndIncrement());
    }

    @SuppressWarnings("unchecked")
    public static void main(String[] args) throws InterruptedException {
        Example a = new Example("A");
        ThreadPoolExecutor executor = new ThreadPoolExecutor(50, 100, 60, TimeUnit.SECONDS, new ArrayBlockingQueue(30));
        for (int i = 0; i < 20; i++) {
            executor.execute(a::run);
        }
        executor.shutdown();
    }
}