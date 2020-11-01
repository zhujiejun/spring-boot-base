package com.zhujiejun.java.disrp.order;

import com.lmax.disruptor.EventHandler;

public class SecondEventHandler implements EventHandler<OrderEvent> {
    @Override
    public void onEvent(OrderEvent event, long sequence, boolean endOfBatch) throws Exception {
        event.setId(event.getId().concat("-2"));
        System.out.println("----------2.SecondEventHandler: the order id is " + event.getId() + "----------");
    }
}
