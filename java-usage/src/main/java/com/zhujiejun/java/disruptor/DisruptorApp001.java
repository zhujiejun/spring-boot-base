package com.zhujiejun.java.disruptor;

import com.lmax.disruptor.BlockingWaitStrategy;
import com.lmax.disruptor.EventFactory;
import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.WaitStrategy;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

//队列中的元素
class Element {
    private int value;

    public int get() {
        return value;
    }

    public void set(int value) {
        this.value = value;
    }
}

public class DisruptorApp001 {
    public static void main(String[] args) throws Exception {
        //指定RingBuffer的大小
        int bufferSize = 16;

        //RingBuffer生产工厂,初始化RingBuffer的时候使用
        EventFactory<Element> eventFactory = Element::new;

        //生产者的线程工厂
        ThreadFactory threadFactory = r -> new Thread(r, "simpleThread");

        //阻塞策略
        WaitStrategy waitStrategy = new BlockingWaitStrategy();

        //处理Event的handler
        EventHandler<Element> eventHandler = (element, sequence, endOfBatch) -> System.out.println("Element: " + element.get());

        //创建disruptor,采用单生产者模式
        Disruptor<Element> disruptor = new Disruptor(eventFactory, bufferSize, threadFactory, ProducerType.SINGLE, waitStrategy);

        //设置EventHandler
        disruptor.handleEventsWith(eventHandler);

        //启动disruptor的线程
        disruptor.start();

        //获取RingBuffer
        RingBuffer<Element> ringBuffer = disruptor.getRingBuffer();
        for (int l = 0; true; l++) {
            //获取下一个可用位置的下标
            long sequence = ringBuffer.next();
            try {
                //返回可用位置的元素
                Element event = ringBuffer.get(sequence);
                //设置该位置元素的值
                event.set(l);
            } finally {
                ringBuffer.publish(sequence);
            }
            TimeUnit.MILLISECONDS.sleep(10);
        }
    }
}
