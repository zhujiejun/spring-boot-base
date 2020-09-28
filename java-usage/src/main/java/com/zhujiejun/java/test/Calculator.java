package com.zhujiejun.java.test;

import com.google.common.collect.ImmutableList;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.List;

public class Calculator {

    private static final BigDecimal INIT = new BigDecimal("11173.93");
    private static final String PATH = "/home/cat/Downloads/Balance.log";
    private static BigDecimal BALANCE = INIT;
    //private static AtomicInteger COUNT = new AtomicInteger(0);

    private static final List<String> AMOUNT = ImmutableList.of(
            "2,327.50",
            "9,803.85",
            "22.49",
            "-100.10",
            "-184.00",
            "-8.00",
            "184.00",
            "-174.00",
            "-27.00",
            "-5.00",
            "-5.00",
            "-1,480.00",
            "-5.00",
            "-5.00",
            "-10.00",
            "-16.00",
            "-7.50",
            "-16.00",
            "-1,820.00",
            "-10.00",
            "-5.00",
            "-11.00",
            "-5.00",
            "-5.00",
            "-2,148.34",
            "-5.00",
            "-55.00",
            "-5.00",
            "-5.00",
            "5,321.70",
            "-5.00",
            "-5.00",
            "-5.00",
            "-13.00",
            "-15.00",
            "-4.50",
            "-5.00",
            "-5.00",
            "-20.00",
            "-5.00",
            "-5.00",
            "-5.00",
            "-5.00",
            "-5.00",
            "-5.00",
            "9,870.35",
            "-5.00",
            "-5.00",
            "-4.00",
            "-5.00",
            "-7.00",
            "-7.00",
            "-100.00",
            "-490.00",
            "-3.00",
            "-15.00",
            "-15.00",
            "750.00",
            "177.50",
            "5,321.70",
            "-1,337.96",
            "-20.00",
            "550.00",
            "12,078.35",
            "-200.00",
            "-12.00",
            "-2,000.00",
            "5,321.70",
            "-100.00",
            "900.00",
            "9,840.35",
            "35.77",
            "-3,000.00",
            "-2,179.06",
            "5,321.70",
            "12,705.35",
            "5,321.70",
            "-600.00",
            "9,127.85",
            "-100.10",
            "5,321.70",
            "-1,000.00",
            "9,873.35",
            "59.47",
            "-1,000.00",
            "5,071.70",
            "-900.00",
            "900.00",
            "-900.00",
            "-602.47",
            "9,899.85",
            "1,860.28",
            "-1,005.00",
            "5,321.70",
            "-5.00",
            "-5.00",
            "-5.00",
            "-1,051.49",
            "9,840.34",
            "-5.00",
            "-2.40",
            "-200.00",
            "-49.83",
            "-1,040.00",
            "-100.00",
            "5,321.70",
            "-100.00",
            "9,840.35",
            "-1,898.05",
            "82.92",
            "-100.00",
            "-1,010.00",
            "2,274.08",
            "-100.00",
            "-1,600.34",
            "10,842.48",
            "-10.00",
            "100.00",
            "-5.00",
            "-5.00",
            "-32.30",
            "-5.00",
            "-100.00",
            "-474.64",
            "-1,040.00",
            "10,407.69",
            "-1.60",
            "-1.60",
            "-3,517.07",
            "-1.60",
            "-1.60",
            "-200.00",
            "-30.00",
            "-311.87",
            "-1,050.00"
    );

    public static void main(String[] args) throws Exception {
        File file = new File(PATH);
        if (!file.exists()) {
            file.createNewFile();
        }
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        PrintStream printStream = new PrintStream(fileOutputStream);
        System.setOut(printStream);

        DecimalFormat decimalFormat = new DecimalFormat("#,###.00");
        AMOUNT.stream().map(amt -> {
            double value = 0;
            try {
                value = decimalFormat.parse(amt).doubleValue();
            } catch (ParseException e) {
                e.printStackTrace();
            }
            return new BigDecimal(value);
        }).forEach(amt -> {
            BALANCE = BALANCE.add(amt);
            System.out.printf("%-8.2f\t\t\t%-8.2f\n", amt, BALANCE);
        });
    }
}
