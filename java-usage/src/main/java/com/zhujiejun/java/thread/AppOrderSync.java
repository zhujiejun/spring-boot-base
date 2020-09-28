package com.zhujiejun.java.thread;

import java.util.stream.IntStream;

public class AppOrderSync {

    /**
     * A-1
     * B-2
     * C-3
     * <p>
     * A-B-C: 1-2-3
     * B-A:C: 2-1-3
     * C-A-B: 3-1-2
     */

    private int flag = 1;

    public synchronized void threadA() {
        while (flag != 1) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.printf("current thread is %s\n", Thread.currentThread().getName());
        flag = 2;
        this.notifyAll();
    }

    public synchronized void threadB() {
        while (flag != 2) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.printf("current thread is %s\n", Thread.currentThread().getName());
        flag = 3;
        this.notifyAll();
    }

    public synchronized void threadC() {
        while (flag != 3) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.printf("current thread is %s\n\n", Thread.currentThread().getName());
        flag = 1;
        this.notifyAll();
    }

    public static void main(String[] args) {
        AppOrderSync order = new AppOrderSync();

        /*Thread threadA = new Thread(order::printA, "thread-A");
        Thread threadB = new Thread(order::printB, "thread-B");
        Thread threadC = new Thread(order::printC, "thread-C");*/

        Thread threadA = new Thread(() -> IntStream.range(0, 5).forEach(i -> order.threadA()), "thread-A");
        Thread threadB = new Thread(() -> IntStream.range(0, 5).forEach(i -> order.threadB()), "thread-B");
        Thread threadC = new Thread(() -> IntStream.range(0, 5).forEach(i -> order.threadC()), "thread-C");

        threadB.start();
        threadA.start();
        threadC.start();
    }
}