package com.zhujiejun.java.thread.blk;

import org.apache.commons.lang3.StringUtils;

import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

class ShareResource {
    private volatile boolean FLAG = true;
    private final BlockingQueue<String> QUEUE;
    private final AtomicInteger atomicInteger = new AtomicInteger();

    public ShareResource(BlockingQueue<String> queue) {
        this.QUEUE = queue;
    }

    private void show(String act, String status) {
        System.out.printf("%s: %s \t %s data [%s] %s\n", System.currentTimeMillis(),
                Thread.currentThread().getName(), act, atomicInteger.toString(), status);
    }

    public void produce() throws Exception {
        while (FLAG) {
            String data = String.valueOf(atomicInteger.getAndIncrement());
            boolean isOffered = QUEUE.offer(data, 2, TimeUnit.SECONDS);
            if (isOffered) {
                show("produce", "success");
            } else {
                show("produce", "failure");
            }
            TimeUnit.MILLISECONDS.sleep(100);
        }
        System.out.println(Thread.currentThread().getName() + "\t" + "stoping for produce....");
    }

    public void consume() throws Exception {
        while (FLAG) {
            String result = QUEUE.poll(2, TimeUnit.SECONDS);
            if (StringUtils.isNotBlank(result)) {
                show("consume", "success\n");
            } else {
                show("consume", "failure\n");
                FLAG = false;
                return;
            }
            //TimeUnit.MILLISECONDS.sleep(1500);
        }
        System.out.println(Thread.currentThread().getName() + "\t" + "stoping for consume....\n");
    }

    public void stop() {
        this.FLAG = false;
    }
}

public class ProdConmDemo {
    public static void main(String[] args) throws Exception {
        Path path = Paths.get("/home/cat/Downloads/tmp/ProdConmDemo.tmp");
        if (Files.notExists(path)) Files.createFile(path);
        System.setOut(new PrintStream(path.toFile()));
        //ShareResource share = new ShareResource(new ArrayBlockingQueue<>(1));
        ShareResource share = new ShareResource(new SynchronousQueue<>());
        new Thread(() -> {
            try {
                share.produce();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "A thread").start();

        new Thread(() -> {
            try {
                share.consume();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "B thread").start();

        TimeUnit.SECONDS.sleep(5);
        share.stop();
    }
}
