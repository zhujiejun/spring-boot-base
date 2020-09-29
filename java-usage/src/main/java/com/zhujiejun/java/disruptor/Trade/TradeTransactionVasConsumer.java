package com.zhujiejun.java.disruptor.Trade;

import com.lmax.disruptor.EventHandler;

//负责处理增值业务的消费者C1
public class TradeTransactionVasConsumer implements EventHandler<TradeTransaction> {
    @Override
    public void onEvent(TradeTransaction event, long sequence, boolean endOfBatch) throws Exception {
        //do something....
    }
}
