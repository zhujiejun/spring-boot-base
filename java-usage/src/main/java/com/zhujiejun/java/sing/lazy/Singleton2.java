package com.zhujiejun.java.sing.lazy;

public class Singleton2 {
    private static Singleton2 INSTANCE;

    private Singleton2() {
    }

    public synchronized static Singleton2 getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Singleton2();
        }
        return INSTANCE;
    }
}
