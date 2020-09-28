package com.zhujiejun.java.thread;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.IntStream;

public class AppCountDown {

    private static volatile int COUNTOR = 0;

    private static final ReentrantLock LOCK = new ReentrantLock();

    private static final CountDownLatch LATCH = new CountDownLatch(100);

    private static final ExecutorService THREAD_POOR = Executors.newFixedThreadPool(120);

    public static void main(String[] args) {
        IntStream.rangeClosed(1, 100).forEach(i -> THREAD_POOR.submit(AppCountDown::increment));
        try {
            LATCH.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("FINALLY, COUNTOR = " + COUNTOR);
        THREAD_POOR.shutdown();
    }

    private static void increment() {
        System.out.println("current thread is " + Thread.currentThread().getName());
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //LOCK.lock();
        COUNTOR++;
        //LOCK.unlock();
        LATCH.countDown();
    }
}
