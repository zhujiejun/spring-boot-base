package com.zhujiejun.java.disrp.trade;

import com.lmax.disruptor.EventTranslator;

import java.util.Random;

//交易网关收到交易P1
public class TradeTransactionEventTranslator implements EventTranslator<TradeTransaction> {
    private static final Random RANDOM = new Random();

    @Override
    public void translateTo(TradeTransaction event, long sequence) {
        this.generateTradeTransaction(event);
    }

    private TradeTransaction generateTradeTransaction(TradeTransaction trade) {
        trade.setPrice(RANDOM.nextDouble() * 9999);
        return trade;
    }
}
