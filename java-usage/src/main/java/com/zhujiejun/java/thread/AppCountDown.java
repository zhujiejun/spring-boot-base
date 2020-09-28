package com.zhujiejun.java.thread;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class AppCountDown {

    private static final String A = "A";
    private static final String B = "B";
    private static final String C = "C";
    private static volatile String ORDER = A;

    private static final int SLEEP = 2000;

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
        THREAD_POOR.submit(AppCountDown::threadc);
        THREAD_POOR.submit(AppCountDown::threadb);
        THREAD_POOR.submit(AppCountDown::threada);
        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        THREAD_POOR.shutdown();
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
        LOCK.lock();
        System.out.println("1.LOCK-CONDITION_A: " + LOCK.hasWaiters(CONDITION_A));
        System.out.println("1.LOCK-CONDITION_B: " + LOCK.hasWaiters(CONDITION_B));
        System.out.println("1.LOCK-CONDITION_C: " + LOCK.hasWaiters(CONDITION_C));
        try {
            while (!ORDER.equals(A)) {
                CONDITION_A.await();
            }
            System.out.println("------this ia thread a------\n");
            TimeUnit.MILLISECONDS.sleep(new Random().nextInt(SLEEP));
            ORDER = B;
            CONDITION_B.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            LOCK.unlock();
        }
    }

    private static void threadb() {
        LOCK.lock();
        System.out.println("2.LOCK-CONDITION_A: " + LOCK.hasWaiters(CONDITION_A));
        System.out.println("2.LOCK-CONDITION_B: " + LOCK.hasWaiters(CONDITION_B));
        System.out.println("2.LOCK-CONDITION_C: " + LOCK.hasWaiters(CONDITION_C));
        try {
            while (!ORDER.equals(B)) {
                CONDITION_B.await();
            }
            System.out.println("------this ia thread b------\n");
            TimeUnit.MILLISECONDS.sleep(new Random().nextInt(SLEEP));
            ORDER = C;
            CONDITION_C.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            LOCK.unlock();
        }
    }

    private static void threadc() {
        LOCK.lock();
        System.out.println("3.LOCK-CONDITION_A: " + LOCK.hasWaiters(CONDITION_A));
        System.out.println("3.LOCK-CONDITION_B: " + LOCK.hasWaiters(CONDITION_B));
        System.out.println("3.LOCK-CONDITION_C: " + LOCK.hasWaiters(CONDITION_C));
        try {
            while (!ORDER.equals(C)) {
                CONDITION_C.await();
            }
            System.out.println("------this ia thread c------\n");
            TimeUnit.MILLISECONDS.sleep(new Random().nextInt(SLEEP));
            ORDER = A;
            CONDITION_A.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            LOCK.unlock();
        }
    }
}
