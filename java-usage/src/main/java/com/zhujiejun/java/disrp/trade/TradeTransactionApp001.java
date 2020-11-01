package com.zhujiejun.java.disrp.trade;

import com.google.common.base.Stopwatch;
import com.lmax.disruptor.BusySpinWaitStrategy;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.EventHandlerGroup;
import com.lmax.disruptor.dsl.ProducerType;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class TradeTransactionApp001 {
    private static final int BUFFER_SIZE = 1024;
    private static final CountDownLatch LATCH = new CountDownLatch(1);
    private static final ExecutorService THREAD_POOL = Executors.newFixedThreadPool(5);
    private static final String SAVE_PATH = "/opt/data/spring/boot/java-usage/TradeTransaction.tmp";

    public static void main(String[] args) throws Exception {
        /*File file = new File(SAVE_PATH);
        if (!file.exists()) file.createNewFile();
        System.setOut(new PrintStream(new FileOutputStream(file)));*/

        Stopwatch watch = Stopwatch.createStarted();
        Disruptor<TradeTransaction> disruptor = new Disruptor<>(TradeTransaction::new, BUFFER_SIZE, THREAD_POOL,
                ProducerType.SINGLE, new BusySpinWaitStrategy());

        EventHandlerGroup<TradeTransaction> handlerGroup = disruptor.handleEventsWith(new TradeTransactionVasConsumer(),
                new TradeTransactionInDBHandler());

        TradeTransactionJMSNotifyHandler jmsNotifyHandler = new TradeTransactionJMSNotifyHandler();
        //声明在C1,C2完事之后执行JMS消息发送操作 也就是流程走到C3
        handlerGroup.then(jmsNotifyHandler);

        disruptor.start();
        THREAD_POOL.submit(new TradeTransactionPublisher(LATCH, disruptor));
        LATCH.await();//等待生产者完事.
        disruptor.shutdown();
        THREAD_POOL.shutdown();
        System.out.println("total time consumption is " + watch.elapsed(TimeUnit.MILLISECONDS) + " ms, approximately "
                + watch.elapsed(TimeUnit.SECONDS) + " s.");
    }
}
