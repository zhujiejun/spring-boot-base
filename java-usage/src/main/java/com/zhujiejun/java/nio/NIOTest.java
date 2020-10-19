package com.zhujiejun.java.nio;

import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SeekableByteChannel;
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
    }
}
