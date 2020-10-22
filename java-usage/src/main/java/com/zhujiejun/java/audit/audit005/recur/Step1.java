package com.zhujiejun.java.audit.audit005.recur;

import com.google.common.base.Stopwatch;

import java.util.concurrent.TimeUnit;

public class Step1 {
    public static int recur(int max) {
        if (max < 1) {
            throw new IllegalArgumentException("max cloud less than 1");
        }
        if (max == 1 || max == 2) {
            return max;
        }
        return recur(max - 1) + recur(max - 2);
    }

    public static void main(String[] args) {
        Stopwatch watch = Stopwatch.createStarted();
        int result = recur(40);
        System.out.printf("the result is %d\n", result);
        long ms = watch.elapsed(TimeUnit.MILLISECONDS);
        System.out.printf("total time condumption is %d ms\n", ms);
    }
}
