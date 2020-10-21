package com.zhujiejun.java.audit.audit003;

public class Father {
    private int i = test();
    private static int j = method();

    static {
        System.out.println("(01)");
    }

    Father() {
        System.out.println("(02)");
    }

    {
        System.out.println("(03)");
    }

    public int test() {
        System.out.println("(04)");
        return 1;
    }

    public static int method() {
        System.out.println("(05)");
        return 1;
    }
}
