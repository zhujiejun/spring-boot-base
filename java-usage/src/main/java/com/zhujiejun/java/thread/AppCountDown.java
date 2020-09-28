package com.zhujiejun.java.thread;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class AppCountDown {

    private static final int SLEEP = 1000;

    private static volatile int COUNTOR = 0;

    private static final ReentrantLock LOCK = new ReentrantLock();

    private static final Condition CONDITION_A = LOCK.newCondition();

    private static final Condition CONDITION_B = LOCK.newCondition();

    private static final Condition CONDITION_C = LOCK.newCondition();

    private static final CountDownLatch LATCH = new CountDownLatch(10);

    private static final ExecutorService THREAD_POOR = Executors.newFixedThreadPool(15);

    public static void main(String[] args) {
        /*Stopwatch stopwatch = Stopwatch.createStarted();
        IntStream.rangeClosed(1, 10).forEach(i -> THREAD_POOR.submit(AppCountDown::increment));
        try {
            LATCH.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("finally, countor = " + COUNTOR);
        THREAD_POOR.shutdown();
        System.out.println("total time consumption is " + stopwatch.elapsed(TimeUnit.NANOSECONDS) + " ns.");*/
        /*--------------------------------------------------------------------------------------------------*/
        THREAD_POOR.submit(AppCountDown::threada);
        THREAD_POOR.submit(AppCountDown::threadb);
        THREAD_POOR.submit(AppCountDown::threadc);
        /*try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        THREAD_POOR.shutdown();*/
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

    /**
     * c-a-b
     * b-c-a
     * a-c-b
     * b-c-a
     */
    private static void threada() {
        try {
            CONDITION_A.wait();
            System.out.println("------this ia thread a------");
            TimeUnit.MILLISECONDS.sleep(new Random().nextInt(SLEEP));
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            CONDITION_B.signal();
        }
    }

    private static void threadb() {
        try {
            CONDITION_B.wait();
            System.out.println("------this ia thread b------");
            TimeUnit.MILLISECONDS.sleep(new Random().nextInt(SLEEP));
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            CONDITION_C.signal();
        }
    }

    private static void threadc() {
        try {
            CONDITION_C.wait();
            System.out.println("------this ia thread c------");
            TimeUnit.MILLISECONDS.sleep(new Random().nextInt(SLEEP));
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            CONDITION_A.signal();
        }
    }
}
