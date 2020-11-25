package com.zhujiejun.java.jni;

public class JNIDemo {
    static {
        //-Djava.library.path=/opt/workspace/java/spring-boot-base/java-usage/lib
        //String libPath = "/opt/workspace/java/spring-boot-base/java-usage/lib";
        //System.out.println(System.getProperty("java.library.path"));
        //System.setProperty("java.library.path", libPath);
        System.loadLibrary("jni-base");
    }
    public static native long hello(int max);

    public static void main(String[] args) {
        long hello = hello(1234567);
        System.out.println(hello);
    }
}
