package com.zhujiejun.java.audit.audit005.recur;

import com.google.common.base.Stopwatch;

import java.math.BigInteger;
import java.util.concurrent.TimeUnit;

public class Step1 {
    public static BigInteger recur(BigInteger max) {
        if (max.compareTo(new BigInteger("1")) < 0) {
            throw new IllegalArgumentException("max cloud less than 1");
        }
        if (max.compareTo(new BigInteger("1")) == 0 ||
                max.compareTo(new BigInteger("2")) == 0) {
            return max;
        }
        return recur(max.subtract(new BigInteger("1"))).add(recur(max.subtract(new BigInteger("2"))));
    }

    public static void main(String[] args) {
        Stopwatch watch = Stopwatch.createStarted();
        BigInteger result = recur(new BigInteger("1000"));
        System.out.printf("the result is %s\n", result.toString());
        long ms = watch.elapsed(TimeUnit.MILLISECONDS);
        System.out.printf("total time consumption is %d ms.\n", ms);
    }
}
