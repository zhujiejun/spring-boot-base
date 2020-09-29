package com.zhujiejun.java.disruptor.Trade;

import com.google.common.base.Stopwatch;
import com.lmax.disruptor.BusySpinWaitStrategy;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.EventHandlerGroup;
import com.lmax.disruptor.dsl.ProducerType;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class TradeTransactionExecutor {
    private static final int bufferSize = 1024;
    private static final CountDownLatch latch = new CountDownLatch(1);
    private static final ExecutorService executor = Executors.newFixedThreadPool(4);
    private static final String SAVE_PATH = "/home/cat/Downloads/TradeTransaction.tmp";

    public static void main(String[] args) throws Exception {
        File file = new File(SAVE_PATH);
        if (!file.exists()) file.createNewFile();
        System.setOut(new PrintStream(new FileOutputStream(file)));

        Stopwatch watch = Stopwatch.createStarted();
        Disruptor<TradeTransaction> disruptor = new Disruptor<>(TradeTransaction::new, bufferSize, executor,
                ProducerType.SINGLE, new BusySpinWaitStrategy());

        EventHandlerGroup<TradeTransaction> handlerGroup = disruptor.handleEventsWith(new TradeTransactionVasConsumer(),
                new TradeTransactionInDBHandler());

        TradeTransactionJMSNotifyHandler jmsConsumer = new TradeTransactionJMSNotifyHandler();
        //声明在C1,C2完事之后执行JMS消息发送操作 也就是流程走到C3
        handlerGroup.then(jmsConsumer);

        disruptor.start();
        executor.submit(new TradeTransactionPublisher(latch, disruptor));
        latch.await();//等待生产者完事.
        disruptor.shutdown();
        executor.shutdown();
        System.out.println("total time consumption is " + watch.elapsed(TimeUnit.MILLISECONDS) + " ms.");
    }
}
