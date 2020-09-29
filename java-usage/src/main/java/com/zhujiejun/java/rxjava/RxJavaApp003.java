package com.zhujiejun.java.rxjava;

import com.google.common.base.Stopwatch;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.schedulers.Schedulers;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.concurrent.TimeUnit;

@SuppressWarnings("all")
public class RxJavaApp003 {

    private static final String SAVE_PATH = "/home/cat/Downloads/ObserverApp003.tmp";

    public static void main(String[] args) {
        File file = new File(SAVE_PATH);
        try {
            if (!file.exists()) file.createNewFile();
            System.setOut(new PrintStream(new FileOutputStream(file)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Stopwatch watch = Stopwatch.createStarted();
        Flowable.range(1, 100)
                .observeOn(Schedulers.computation())
                .map(v -> v * v)
                .blockingSubscribe(System.out::println);
        System.out.println("1.total time consumption is " + watch.elapsed(TimeUnit.MILLISECONDS) + " ms.");
        System.out.println("----------------------------------");
        watch.reset().start();
        Flowable.range(1, 100)
                .flatMap(v -> Flowable.just(v)
                        .subscribeOn(Schedulers.computation())
                        .map(w -> w * w)
                ).blockingSubscribe(System.out::println);
        System.out.println("2.total time consumption is " + watch.elapsed(TimeUnit.MILLISECONDS) + " ms.");
        System.out.println("----------------------------------");
    }
}
