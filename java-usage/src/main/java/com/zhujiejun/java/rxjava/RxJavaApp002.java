package com.zhujiejun.java.rxjava;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.schedulers.Schedulers;

import static java.lang.Thread.sleep;

public class RxJavaApp002 {
    public static void main(String[] args) {
        Observable.create((ObservableOnSubscribe<Integer>) emitter -> {
            emitter.onNext(123);
            sleep(1000);
            emitter.onNext(456);
        }).observeOn(Schedulers.computation())
                .subscribeOn(Schedulers.computation())
                .subscribe(System.out::println);
    }
}
