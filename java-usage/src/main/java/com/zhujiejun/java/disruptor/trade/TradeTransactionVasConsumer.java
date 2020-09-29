package com.zhujiejun.java.disruptor.trade;

import com.lmax.disruptor.EventHandler;

import java.util.Random;
import java.util.concurrent.TimeUnit;

//负责处理增值业务的消费者C1
public class TradeTransactionVasConsumer implements EventHandler<TradeTransaction> {
    @Override
    public void onEvent(TradeTransaction event, long sequence, boolean endOfBatch) throws Exception {
        //do something...
        System.out.println("----------负责处理增值业务的消费者C1处理交易中...-----------");
        TimeUnit.MILLISECONDS.sleep(new Random().nextInt(1000));
    }
}
