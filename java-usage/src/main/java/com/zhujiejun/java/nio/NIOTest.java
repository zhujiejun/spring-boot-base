package com.zhujiejun.java.nio;

import java.net.InetSocketAddress;
import java.net.StandardSocketOptions;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SeekableByteChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class NIOTest {
    public static void main(String[] args) throws Exception {
        Path path = Paths.get("/", "home", "cat", "Downloads", "nio", "info.tmp");
        Path parent = path.getParent();
        if (Files.notExists(parent)) {
            Files.createDirectory(parent);
        }
        if (Files.notExists(path)) {
            Files.createFile(path);
        }

        int length = path.toAbsolutePath().toString().getBytes().length;

        FileChannel writeChannel = FileChannel.open(path, StandardOpenOption.TRUNCATE_EXISTING,
                StandardOpenOption.WRITE);
        ByteBuffer writeBuffer = ByteBuffer.allocate(length);
        writeBuffer.put(path.toAbsolutePath().toString().getBytes());
        writeBuffer.flip();//TODO mandatory
        writeChannel.write(writeBuffer);

        SeekableByteChannel readChannel = Files.newByteChannel(path, StandardOpenOption.READ);
        ByteBuffer readBuffer = ByteBuffer.allocate(length);
        readChannel.read(readBuffer);
        readBuffer.flip();
        System.out.printf("the path is %s\n", new String(readBuffer.array()));
        /*-------------------------------------------------------------------*/
        String hello = "hello";
        Selector selector = Selector.open();
        SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress("192.168.100.100", 3000));

        //write
        /*ByteBuffer socketWriteBuffer = ByteBuffer.allocate(hello.length());
        socketWriteBuffer.put(hello.getBytes());
        socketWriteBuffer.flip();
        socketChannel.write(socketWriteBuffer);*/

        //read
        socketChannel.configureBlocking(false);
        socketChannel.setOption(StandardSocketOptions.SO_KEEPALIVE, Boolean.TRUE);
        socketChannel.register(selector, SelectionKey.OP_READ);
        selector.select();
        ByteBuffer socketReadBuffer = ByteBuffer.allocate(10);
        socketChannel.read(socketReadBuffer);
        String msg = new String(socketReadBuffer.array());
        System.out.println(msg);
    }
}
