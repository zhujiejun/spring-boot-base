package com.zhujiejun.java.disrp.trade;

import com.lmax.disruptor.EventHandler;

import java.util.Random;
import java.util.concurrent.TimeUnit;

//负责处理增值业务的消费者C1
public class TradeTransactionVasConsumer implements EventHandler<TradeTransaction> {
    @Override
    public void onEvent(TradeTransaction event, long sequence, boolean endOfBatch) throws Exception {
        //do something...
        System.out.println("----------" + sequence + ".消费者C1 处理增值业务中...-----------");
        System.out.println("----------" + sequence + ".消费者C1 current price is " + event.getPrice() + "-----------");
        TimeUnit.MILLISECONDS.sleep(new Random().nextInt(1000));
    }
}
