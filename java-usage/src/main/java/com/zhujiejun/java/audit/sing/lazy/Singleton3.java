package com.zhujiejun.java.audit.sing.lazy;

public class Singleton3 {
    private Singleton3() {
    }

    private static class Inner {
        private static final Singleton3 INSTANCE = new Singleton3();
    }

    public static Singleton3 getInstance() {
        return Inner.INSTANCE;
    }
}
