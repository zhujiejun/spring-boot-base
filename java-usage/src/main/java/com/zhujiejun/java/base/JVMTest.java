package com.zhujiejun.java.base;

public class JVMTest {
    public static void main(String[] args) {
        JVMStack stack = new MyJVMStack2();
        try {
            stack.msg();
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }
}
