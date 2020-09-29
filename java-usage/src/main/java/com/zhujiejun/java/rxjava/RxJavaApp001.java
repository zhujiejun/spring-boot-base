package com.zhujiejun.java.rxjava;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;

import java.util.stream.IntStream;

public class RxJavaApp001 {
    public static void main(String[] args) {
        //被观察者
        Observable<String> novel = Observable.create(emitter -> {
            IntStream.rangeClosed(1, 100).forEach(suffix -> {
                emitter.onNext(String.format("content%d", suffix));
            });
            emitter.onComplete();
        });
        //观察者
        Observer<String> reader = new Observer<String>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                System.out.println("1.onSubscribe");
            }

            @Override
            public void onNext(@NonNull String s) {
                System.out.println("2.the str is " + s);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                System.out.println("4.onError");
            }

            @Override
            public void onComplete() {
                System.out.println("3.onComplete");
            }
        };
        novel.subscribe(reader);
    }

}
