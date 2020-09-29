package com.zhujiejun.java.disruptor.trade;

import com.lmax.disruptor.dsl.Disruptor;

import java.util.concurrent.CountDownLatch;

public class TradeTransactionPublisher implements Runnable {
    private final CountDownLatch latch;
    private final Disruptor<TradeTransaction> disruptor;
    //private static final int LOOP = 10000000;//模拟一千万次交易的发生
    private static final int LOOP = 10;

    public TradeTransactionPublisher(CountDownLatch latch, Disruptor<TradeTransaction> disruptor) {
        this.disruptor = disruptor;
        this.latch = latch;
    }

    @Override
    public void run() {
        TradeTransactionEventTranslator tradeTransloator = new TradeTransactionEventTranslator();
        for (int i = 0; i < LOOP; i++) {
            disruptor.publishEvent(tradeTransloator);
        }
        latch.countDown();
    }
}
