package com.zhujiejun.java.jni;

import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;
import java.util.TreeMap;

/**
 * JNI DAYA TYPE
 * JVM System Args
 * JVM Bootstrapt Args
 * -Djava.library.path=/opt/workspace/java/spring-boot-base/java-usage/lib
 */
public class JNIDemo {
    static {
        System.loadLibrary("jni-base");
    }

    public static native long hello(int max);

    public static void main(String[] args) throws Exception {
        Path path = Paths.get(System.getProperty("jni.save.path"));
        if (Files.notExists(path)) Files.createFile(path);
        System.setOut(new PrintStream(path.toFile()));
        Properties properties = System.getProperties();
        new TreeMap<>(properties).forEach((k, v) -> System.out.println(k.toString().concat(": ").concat(v.toString())));
        System.out.println("------------");
        long hello = hello(12345678);
        System.out.println(hello);
    }
}
