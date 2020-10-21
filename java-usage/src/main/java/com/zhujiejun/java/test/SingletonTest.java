package com.zhujiejun.java.test;

import com.zhujiejun.java.audit.audit002.hungry.Singleton4;
import com.zhujiejun.java.audit.audit002.hungry.Singleton5;
import com.zhujiejun.java.audit.audit002.hungry.Singleton6;
import com.zhujiejun.java.audit.audit002.lazy.Singleton1;
import com.zhujiejun.java.audit.audit002.lazy.Singleton2;
import com.zhujiejun.java.audit.audit002.lazy.Singleton3;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SingletonTest {

    private final static ExecutorService THREAD_POOL = Executors.newFixedThreadPool(2);

    public static void main(String[] args) throws Exception {
        Singleton1 s1 = THREAD_POOL.submit(Singleton1::getInstance).get();
        Singleton1 s2 = THREAD_POOL.submit(Singleton1::getInstance).get();
        System.out.println(s1);
        System.out.println(s1);
        System.out.printf("s1==s2:%s\n", s1 == s2);
        System.out.println("------------------");

        Singleton2 s3 = THREAD_POOL.submit(Singleton2::getInstance).get();
        Singleton2 s4 = THREAD_POOL.submit(Singleton2::getInstance).get();
        System.out.println(s3);
        System.out.println(s4);
        System.out.printf("s3==s4:%s\n", s3 == s4);
        System.out.println("------------------");

        Singleton3 s5 = THREAD_POOL.submit(Singleton3::getInstance).get();
        Singleton3 s6 = THREAD_POOL.submit(Singleton3::getInstance).get();
        System.out.println(s5);
        System.out.println(s6);
        System.out.printf("s5==s6:%s\n", s5 == s6);
        System.out.println("------------------");

        Singleton4 s7 = Singleton4.INSTANCE;
        Singleton4 s8 = Singleton4.INSTANCE;
        System.out.println(s7);
        System.out.println(s8);
        System.out.printf("s7==s8:%s\n", s7 == s8);
        System.out.println("------------------");

        Singleton5 s9 = Singleton5.INSTANCE;
        Singleton5 s10 = Singleton5.INSTANCE;
        System.out.println(s9);
        System.out.println(s10);
        System.out.printf("s9==s10:%s\n", s9 == s10);
        System.out.println("------------------");

        Singleton6 s11 = Singleton6.INSTANCE;
        Singleton6 s12 = Singleton6.INSTANCE;
        System.out.println(s11);
        System.out.println(s12);
        System.out.printf("s11==s12:%s\n", s11 == s12);
        System.out.println("------------------");

        THREAD_POOL.shutdown();
    }
}
