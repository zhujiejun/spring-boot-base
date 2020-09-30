package com.zhujiejun.java.disruptor.order;

import com.lmax.disruptor.EventHandler;

public class LastEventHandler implements EventHandler<OrderEvent> {
    @Override
    public void onEvent(OrderEvent event, long sequence, boolean endOfBatch) throws Exception {
        event.setId(event.getId().concat("-last"));
        System.out.println("----------5.LastEventHandler: the order id is " + event.getId() + "----------");
    }
}
