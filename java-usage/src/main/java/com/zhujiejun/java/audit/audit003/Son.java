package com.zhujiejun.java.audit.audit003;

public class Son extends Father {
    private int i = test();
    private static int j = method();

    static {
        System.out.println("(06)");
    }

    Son() {
        System.out.println("(07)");
    }

    {
        System.out.println("(08)");
    }

    public int test() {
        System.out.println("(09)");
        return 1;
    }

    public static int method() {
        System.out.println("(10)");
        return 1;
    }

    /**
     * (05)
     * (01)
     * (10)
     * (06)
     * <p>
     * (09)
     * (03)
     * (02)
     * <p>
     * (09)
     * (08)
     * (07)
     * <p>
     * (09)
     * (03)
     * (02)
     * (09)
     * (08)
     * (07)
     *
     * @param args
     */
    public static void main(String[] args) {
        Son s1 = new Son();
        System.out.println();
        Son s2 = new Son();
    }
}
