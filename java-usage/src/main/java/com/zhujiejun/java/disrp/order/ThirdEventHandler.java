package com.zhujiejun.java.disrp.order;

import com.lmax.disruptor.EventHandler;

public class ThirdEventHandler implements EventHandler<OrderEvent> {
    @Override
    public void onEvent(OrderEvent event, long sequence, boolean endOfBatch) throws Exception {
        event.setId(event.getId().concat("-3"));
        System.out.println("----------3.ThirdEventHandler: the order id is " + event.getId() + "----------");
    }
}
