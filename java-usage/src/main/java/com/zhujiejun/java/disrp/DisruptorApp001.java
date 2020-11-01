package com.zhujiejun.java.disrp;

import com.google.common.base.Stopwatch;
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

        //阻塞策略
        WaitStrategy waitStrategy = new BlockingWaitStrategy();

        //生产者的线程工厂
        ThreadFactory threadFactory = code -> new Thread(code, "SimpleThread");

        //RingBuffer生产工厂,初始化RingBuffer的时候使用
        EventFactory<Element> eventFactory = Element::new;

        //处理Event的handler
        EventHandler<Element> eventHandler = (element, sequence, endOfBatch) -> System.out.println("Element: " + element.get());

        //创建disruptor,采用单生产者模式
        //Disruptor<Element> disruptor = new Disruptor(eventFactory, bufferSize, threadFactory, ProducerType.SINGLE, waitStrategy);
        Disruptor<Element> disruptor = new Disruptor(eventFactory, bufferSize, threadFactory, ProducerType.MULTI, waitStrategy);

        //设置EventHandler
        disruptor.handleEventsWith(eventHandler);

        //启动disruptor的线程
        disruptor.start();

        Stopwatch watch = Stopwatch.createStarted();
        //获取RingBuffer对象
        RingBuffer<Element> ringBuffer = disruptor.getRingBuffer();
        for (int num = 0; num < 1000; num++) {
            //获取下一个可用位置的下标
            long index = ringBuffer.next();
            try {
                //返回可用位置的元素
                Element event = ringBuffer.get(index);
                //设置该位置元素的值
                event.set(num);
            } finally {
                ringBuffer.publish(index);
            }
            TimeUnit.MILLISECONDS.sleep(15);
        }
        System.out.println("total time consumption is " + watch.elapsed(TimeUnit.NANOSECONDS) + " nas.");

        //关闭disruptor的线程
        disruptor.shutdown();
    }
}
