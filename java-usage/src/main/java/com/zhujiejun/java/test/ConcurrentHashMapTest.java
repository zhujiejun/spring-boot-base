package com.zhujiejun.java.test;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

@SuppressWarnings("all")
public class ConcurrentHashMapTest {

    private static final CountDownLatch LATCH = new CountDownLatch(6);
    //private static final Map<String, String> map = new HashMap<>();
    private static final Map<String, String> map = new ConcurrentHashMap<>();
    private static final ExecutorService THREAD_POOL = Executors.newFixedThreadPool(6);

    private static void add(int begin, int end) {
        IntStream.rangeClosed(begin, end).forEach(i -> map.put(String.format("k%03d", i), String.format("v%03d", i)));
        try {
            TimeUnit.MILLISECONDS.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        LATCH.countDown();
    }

    private static void delete(String k) {
        map.remove(k);
        LATCH.countDown();
    }

    private static String read(String k) {
        LATCH.countDown();
        return map.get(k);
    }

    private static void write(String k, String v) {
        try {
            TimeUnit.MILLISECONDS.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        map.put(k, v);
        System.out.printf("write: current k-v is %s-%s\n", k, v);
        LATCH.countDown();
    }

    public static void main(String[] args) throws Exception {
        THREAD_POOL.submit(() -> add(1, 20));
        THREAD_POOL.submit(() -> delete("k003"));
        THREAD_POOL.submit(() -> add(21, 21));
        THREAD_POOL.submit(() -> delete("k008"));
        THREAD_POOL.submit(() -> delete("k021"));
        THREAD_POOL.submit(() -> add(22, 25));
        LATCH.await();
        //delete("k003");
        System.out.println(map.keySet().stream().count());
        map.entrySet().stream().sorted((a, b) -> a.getKey().concat("-").concat(a.getValue())
                .compareTo(b.getKey().concat("-").concat(b.getValue())))
                .forEach(entry -> System.out.printf("%s---%s%n", entry.getKey(), entry.getValue()));

        /*THREAD_POOL.submit(() -> put(1, 50));
        THREAD_POOL.submit(() -> put(25, 75));
        LATCH.await();
        map.forEach((k, v) -> System.out.printf("%s-%s%n\n", k, v));
        System.out.println(map.keySet().stream().count());
        //.sorted(String::compareTo).forEach(System.out::println);*/
        THREAD_POOL.shutdown();
    }
}
