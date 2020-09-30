package com.zhujiejun.java.disruptor.order;

import com.google.common.base.Stopwatch;
import com.lmax.disruptor.BlockingWaitStrategy;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;
import org.apache.commons.lang3.RandomStringUtils;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

public class OrderApp001 {
    private static final int BUFFER_SIZE = 8;
    private static final CountDownLatch LATCH = new CountDownLatch(1);
    private static final ThreadFactory THREAD_FACTORY = Executors.defaultThreadFactory();
    private static final ExecutorService THREAD_POOL = Executors.newFixedThreadPool(8);

    public static void main(String[] args) throws Exception {
        FirstEventHandler firstEventHandler = new FirstEventHandler();
        SecondEventHandler secondHandler = new SecondEventHandler();
        ThirdEventHandler thirdEventHandler = new ThirdEventHandler();
        FourthEventHandler fourthEventHandler = new FourthEventHandler();
        LastEventHandler lastEventHandler = new LastEventHandler();

        Disruptor<OrderEvent> disruptor = new Disruptor<>(OrderEvent::new, BUFFER_SIZE, THREAD_FACTORY,
                ProducerType.SINGLE, new BlockingWaitStrategy());

        //1,2,last顺序执行
        //disruptor.handleEventsWith(new LongEventHandler()).handleEventsWith(new SecondEventHandler())
        //       .handleEventsWith(new LastEventHandler());

        //也是1,2,last顺序执行
        //disruptor.handleEventsWith(firstEventHandler);
        //disruptor.after(firstEventHandler).handleEventsWith(secondHandler).then(lastEventHandler);

        //1,2并发执行,之后才是last
        //disruptor.handleEventsWith(firstEventHandler, secondHandler);
        //disruptor.after(firstEventHandler, secondHandler).handleEventsWith(lastEventHandler);

        //1后2,3后4,1和3并发,2和4都结束后last
        disruptor.handleEventsWith(firstEventHandler, thirdEventHandler);
        disruptor.after(firstEventHandler).handleEventsWith(secondHandler);
        disruptor.after(thirdEventHandler).handleEventsWith(fourthEventHandler);
        disruptor.after(secondHandler, fourthEventHandler).handleEventsWith(lastEventHandler);

        disruptor.start();
        Stopwatch watch = Stopwatch.createStarted();
        THREAD_POOL.submit(() -> {
            disruptor.publishEvent((orderEvent, sequence) -> orderEvent.setId(RandomStringUtils.randomAlphanumeric(18)));
            LATCH.countDown();
        });
        LATCH.await();
        disruptor.shutdown();
        THREAD_POOL.shutdown();
        System.out.println("total time consumption is  " + watch.elapsed(TimeUnit.MILLISECONDS) + " ms.");
    }
}
