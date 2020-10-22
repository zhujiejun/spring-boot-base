package com.zhujiejun.java.audit.audit004;

import java.util.Arrays;

class MyClass {
    int f = 10;
}

public class Parm {
    public static void change(int p1, String p2, Integer p3, int[] p4, MyClass p5) {
        p1 += 1;
        p2 += "world";
        p3 += 1;
        p4[0] += 1;
        p5.f += 1;
    }

    public static void main(String[] args) {
        int v1 = 1;
        String v2 = "hello";
        Integer v3 = 2;
        int[] v4 = {1, 2, 3, 4, 5};
        MyClass v5 = new MyClass();

        change(v1, v2, v3, v4, v5);

        System.out.println("v1 = " + v1);//value pass
        System.out.println("v2 = " + v2);//value pass
        System.out.println("v3 = " + v3);//reference pass
        System.out.println("v4 = " + Arrays.toString(v4));//reference pass
        System.out.println("v5 = " + v5.f);//reference pass
    }
}
