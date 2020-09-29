//package com.zhujiejun.java.disruptor;
//
//import com.lmax.disruptor.BlockingWaitStrategy;
//import com.lmax.disruptor.dsl.Disruptor;
//import com.lmax.disruptor.dsl.ProducerType;
//
//import java.nio.ByteBuffer;
//import java.util.concurrent.Executors;
//import java.util.concurrent.ThreadFactory;
//
//public class DisruptorApp002 {
//    public static void main(String[] args) {
//        ThreadFactory producerFactory = Executors.defaultThreadFactory();
//        LongEventFactory eventFactory = new LongEventFactory();
//        int bufferSize = 8;
//
//        Disruptor<LongEvent> disruptor = new Disruptor<>(eventFactory, bufferSize, producerFactory, ProducerType.SINGLE, new BlockingWaitStrategy());
//
//        FirstEventHandler firstEventHandler = new FirstEventHandler();
//        SecondEventHandler secondHandler = new SecondEventHandler();
//        ThirdEventHandler thirdEventHandler = new ThirdEventHandler();
//        FourthEventHandler fourthEventHandler = new FourthEventHandler();
//        LastEventHandler lastEventHandler = new LastEventHandler();
//
//        //1,2,last顺序执行
//        //disruptor.handleEventsWith(new LongEventHandler()).handleEventsWith(new SecondEventHandler())
//        //        .handleEventsWith(new LastEventHandler());
//
//        //也是1，2，last顺序执行
//        //disruptor.handleEventsWith(firstEventHandler);
//        //disruptor.after(firstEventHandler).handleEventsWith(secondHandler).then(lastEventHandler);
//
//        //1,2并发执行，之后才是last
//        //disruptor.handleEventsWith(firstEventHandler, secondHandler);
//        //disruptor.after(firstEventHandler, secondHandler).handleEventsWith(lastEventHandler);
//
//        //1后2，3后4，1和3并发，2和4都结束后last
//        disruptor.handleEventsWith(firstEventHandler, thirdEventHandler);
//        disruptor.after(firstEventHandler).handleEventsWith(secondHandler);
//        disruptor.after(thirdEventHandler).handleEventsWith(fourthEventHandler);
//        disruptor.after(secondHandler, fourthEventHandler).handleEventsWith(lastEventHandler);
//
//        disruptor.start();
//
//        RingBuffer<LongEvent> ringBuffer = disruptor.getRingBuffer();
//        LongEventProducer longEventProducer = new LongEventProducer(ringBuffer);
//        ByteBuffer byteBuffer = ByteBuffer.allocate(8);
//        for (long i = 0; i < 10L; i++) {
//            byteBuffer.putLong(0, i);
//            longEventProducer.onData(byteBuffer);
//        }
//
//        disruptor.shutdown();
//    }
//}
