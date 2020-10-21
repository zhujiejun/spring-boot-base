package com.zhujiejun.java.sing.lazy;

public class Singleton1 {
    private static Singleton1 INSTANCE;

    private Singleton1() {
    }

    public static Singleton1 getInstance() {
        if (INSTANCE == null) {
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            INSTANCE = new Singleton1();
        }
        return INSTANCE;
    }
}
