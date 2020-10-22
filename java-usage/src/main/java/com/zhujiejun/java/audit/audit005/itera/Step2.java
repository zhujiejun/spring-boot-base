package com.zhujiejun.java.audit.audit005.itera;

import com.google.common.base.Stopwatch;

import java.math.BigInteger;
import java.util.concurrent.TimeUnit;

public class Step2 {
    public static BigInteger itera(BigInteger max) {
        if (max.compareTo(new BigInteger("1")) < 0) {
            throw new IllegalArgumentException("max cloud less than 1");
        }
        if (max.compareTo(new BigInteger("1")) == 0 ||
                max.compareTo(new BigInteger("2")) == 0) {
            return max;
        }
        BigInteger two = new BigInteger("1");
        BigInteger one = new BigInteger("2");
        BigInteger sum = new BigInteger("0");
        for (BigInteger i = new BigInteger("3");
             i.compareTo(new BigInteger(max.toString())) < 1;
             i = i.add(new BigInteger("1"))) {
            sum = one.add(two);
            two = one;
            one = sum;
        }
        return sum;
    }

    public static void main(String[] args) {
        Stopwatch watch = Stopwatch.createStarted();
        BigInteger result = itera(new BigInteger("1000"));
        System.out.printf("the result is %s\n", result.toString());
        long ms = watch.elapsed(TimeUnit.MILLISECONDS);
        System.out.printf("total time condumption is %d ms.\n", ms);
    }
}
