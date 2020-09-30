package com.zhujiejun.java.disruptor.order;

import com.lmax.disruptor.EventHandler;

public class FourthEventHandler implements EventHandler<OrderEvent> {
    @Override
    public void onEvent(OrderEvent event, long sequence, boolean endOfBatch) throws Exception {
        event.setId(event.getId().concat("-4"));
        System.out.println("----------4.FirstEventHandler: the order id is " + event.getId() + "----------");
    }
}
