package com.zhujiejun.java.pyt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class JavaPython {
    private final static String CMD = "python /home/cat/Downloads/temp/pyth/cifar.py";

    public static void main(String[] args) {
        try {
            String line;
            Process proc = Runtime.getRuntime().exec(CMD);
            BufferedReader reader = new BufferedReader(new InputStreamReader(proc.getInputStream()));
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
            reader.close();
            proc.waitFor();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
