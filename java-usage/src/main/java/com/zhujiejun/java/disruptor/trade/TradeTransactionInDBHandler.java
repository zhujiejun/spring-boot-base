package com.zhujiejun.java.disruptor.trade;

import com.lmax.disruptor.EventHandler;

import java.util.UUID;

//负责数据存储的消费者C2
public class TradeTransactionInDBHandler implements EventHandler<TradeTransaction> {
    @Override
    public void onEvent(TradeTransaction event, long sequence, boolean endOfBatch) throws Exception {
        System.out.println("----------消费者C2数据存储中...-----------");
        this.onEvent(event);
    }

    public void onEvent(TradeTransaction event) throws Exception {
        event.setId(UUID.randomUUID().toString());
        //System.out.println(event.getId());
    }
}
