package com.zhujiejun.java.disruptor.order;

import com.lmax.disruptor.EventHandler;

public class FirstEventHandler implements EventHandler<OrderEvent> {
    @Override
    public void onEvent(OrderEvent event, long sequence, boolean endOfBatch) throws Exception {
        event.setId(event.getId().concat("-1"));
        System.out.println("----------1.FirstEventHandler: the order id is " + event.getId() + "----------");
    }
}
