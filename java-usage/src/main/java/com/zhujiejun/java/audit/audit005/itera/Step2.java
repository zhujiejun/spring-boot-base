package com.zhujiejun.java.audit.audit005.itera;

import com.google.common.base.Stopwatch;

import java.util.concurrent.TimeUnit;

public class Step2 {
    public static int itera(int max) {
        if (max < 1) {
            throw new IllegalArgumentException("max cloud less than 1");
        }
        if (max == 1 || max == 2) {
            return max;
        }
        int two = 1;
        int one = 2;
        int sum = 0;
        for (int i = 3; i <= max; ++i) {
            sum = one + two;
            two = one;
            one = sum;
        }
        return sum;
    }

    public static void main(String[] args) {
        Stopwatch watch = Stopwatch.createStarted();
        int result = itera(100);
        System.out.printf("the result is %d\n", result);
        long ms = watch.elapsed(TimeUnit.MILLISECONDS);
        System.out.printf("total time condumption is %d ms\n", ms);
    }
}
