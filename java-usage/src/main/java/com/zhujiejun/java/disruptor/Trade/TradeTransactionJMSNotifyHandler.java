package com.zhujiejun.java.disruptor.Trade;

import com.lmax.disruptor.EventHandler;

//负责发送JMS消息的消费者C3
public class TradeTransactionJMSNotifyHandler implements EventHandler<TradeTransaction> {
    @Override
    public void onEvent(TradeTransaction event, long sequence, boolean endOfBatch) throws Exception {
        //do something....
    }
}
