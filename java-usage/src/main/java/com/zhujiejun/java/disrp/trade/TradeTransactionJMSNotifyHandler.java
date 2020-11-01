package com.zhujiejun.java.disrp.trade;

import com.lmax.disruptor.EventHandler;

import java.util.Random;
import java.util.concurrent.TimeUnit;

//负责发送JMS消息的消费者C3
public class TradeTransactionJMSNotifyHandler implements EventHandler<TradeTransaction> {
    @Override
    public void onEvent(TradeTransaction event, long sequence, boolean endOfBatch) throws Exception {
        //do something...
        System.out.println("----------" + sequence + ".消费者C3 发送JMS消息中...-----------");
        TimeUnit.MILLISECONDS.sleep(new Random().nextInt(1000));
    }
}
