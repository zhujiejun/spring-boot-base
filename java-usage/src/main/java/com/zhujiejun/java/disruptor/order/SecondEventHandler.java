package com.zhujiejun.java.disruptor.order;

import com.lmax.disruptor.EventHandler;

public class SecondEventHandler implements EventHandler<OrderEvent> {
    @Override
    public void onEvent(OrderEvent event, long sequence, boolean endOfBatch) throws Exception {
        System.out.println("----------2.SecondEventHandler----------");
    }
}
