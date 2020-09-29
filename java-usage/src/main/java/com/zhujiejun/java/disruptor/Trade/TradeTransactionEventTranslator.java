package com.zhujiejun.java.disruptor.Trade;

import com.lmax.disruptor.EventTranslator;

import java.util.Random;

//P1
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
