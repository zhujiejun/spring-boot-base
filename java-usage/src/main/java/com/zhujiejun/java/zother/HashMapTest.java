package com.zhujiejun.java.zother;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

public class HashMapTest {
    public void test001() {
        Map<Integer, String> map = new HashMap<>();
        IntStream.rangeClosed(1, 16).forEach(index -> map.put(index, String.valueOf(index)));
        map.forEach((k, v) -> System.out.printf("----------the current k and value are %s and %s----------\n", k, v));
    }

    public static void main(String[] args) {
        HashMapTest test = new HashMapTest();
        test.test001();
    }
}
