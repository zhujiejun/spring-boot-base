package com.zhujiejun.java.thread;

import com.google.common.base.Stopwatch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.IntStream;

public class AppCountDown {

    private static volatile int COUNTOR = 0;

    private static final ReentrantLock LOCK = new ReentrantLock();

    private static final Condition CONDITION = LOCK.newCondition();

    private static final CountDownLatch LATCH = new CountDownLatch(10);

    private static final ExecutorService THREAD_POOR = Executors.newFixedThreadPool(15);

    public static void main(String[] args) {
        Stopwatch stopwatch = Stopwatch.createStarted();
        IntStream.rangeClosed(1, 10).forEach(i -> THREAD_POOR.submit(AppCountDown::increment));
        try {
            LATCH.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("finally, countor = " + COUNTOR);
        THREAD_POOR.shutdown();
        System.out.println("total time consumption is " + stopwatch.elapsed(TimeUnit.NANOSECONDS) + " ns.");
    }

    /**
     * 2083272965
     * 2087690756
     * <p>
     * 2089607227
     * 2085634892
     * <p>
     * 2091093921
     * 2084919993
     */
    private static void increment() {
        System.out.println("current thread is " + Thread.currentThread().getName());
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        /*LOCK.lock();
        System.out.println("\n1.Hold Count and  Queue Length are " + LOCK.getHoldCount() + " " + LOCK.getQueueLength());
        COUNTOR++;
        LOCK.unlock();
        System.out.println("2.Hold Count and  Queue Length are " + LOCK.getHoldCount() + " " + LOCK.getQueueLength() + "\n");*/

        synchronized (AppCountDown.class) {
            COUNTOR++;
        }
        LATCH.countDown();
    }
}
