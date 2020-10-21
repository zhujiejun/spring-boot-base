package com.zhujiejun.java.audit.sing.hungry;

public class Singleton5 {
    public static Singleton5 INSTANCE;

    static {
        //do something...
        INSTANCE = new Singleton5();
    }

    private Singleton5() {
    }
}
