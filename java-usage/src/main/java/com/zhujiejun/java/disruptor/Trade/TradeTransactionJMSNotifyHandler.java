package com.zhujiejun.java.disruptor.Trade;

import com.lmax.disruptor.EventHandler;

import java.util.Random;
import java.util.concurrent.TimeUnit;

//负责发送JMS消息的消费者C3
public class TradeTransactionJMSNotifyHandler implements EventHandler<TradeTransaction> {
    @Override
    public void onEvent(TradeTransaction event, long sequence, boolean endOfBatch) throws Exception {
        //do something...
        System.out.println("----------负责发送JMS消息的消费者C3发送JMS消息中...-----------");
        TimeUnit.MILLISECONDS.sleep(new Random().nextInt(1000));
    }
}
