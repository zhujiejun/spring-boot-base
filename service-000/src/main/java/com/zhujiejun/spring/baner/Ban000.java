package com.zhujiejun.spring.baner;

import org.springframework.boot.Banner;
import org.springframework.boot.SpringBootVersion;
import org.springframework.boot.ansi.AnsiColor;
import org.springframework.boot.ansi.AnsiOutput;
import org.springframework.boot.ansi.AnsiStyle;
import org.springframework.core.env.Environment;

import java.io.PrintStream;

public class Ban000 implements Banner {

    private static final String[] SHOW_MSG = {
            "春江潮水连海平，海上明月共潮生。",

            "滟滟随波千万里，何处春江无月明！",

            "江流宛转绕芳甸，月照花林皆似霰；",

            "空里流霜不觉飞，汀上白沙看不见。",

            "江天一色无纤尘，皎皎空中孤月轮。",

            "江畔何人初见月？江月何年初照人？",

            "人生代代无穷已，江月年年望相似。",

            "不知江月待何人，但见长江送流水。",

            "白云一片去悠悠，青枫浦上不胜愁。",

            "谁家今夜扁舟子？何处相思明月楼？",

            "可怜楼上月徘徊，应照离人妆镜台。",

            "玉户帘中卷不去，捣衣砧上拂还来。",

            "此时相望不相闻，愿逐月华流照君。",

            "鸿雁长飞光不度，鱼龙潜跃水成文。",

            "昨夜闲潭梦落花，可怜春半不还家。",

            "江水流春去欲尽，江潭落月复西斜。",

            "斜月沉沉藏海雾，碣石潇湘无限路。",

            "不知乘月几人归，落月摇情满江树。"
    };

    private static final String SPRING_BOOT = " 春江花月夜 ";

    private static final int STRAP_LINE_SIZE = 42;

    @Override
    public void printBanner(Environment environment, Class<?> sourceClass, PrintStream printStream) {
        for (String line : SHOW_MSG) {
            printStream.println(line);
        }
        String version = SpringBootVersion.getVersion();
        version = (version != null) ? " (v" + version + ")" : "";
        StringBuilder padding = new StringBuilder();
        while (padding.length() < STRAP_LINE_SIZE - (version.length() + SPRING_BOOT.length())) {
            padding.append(" ");
        }

        printStream.println(AnsiOutput.toString(AnsiColor.GREEN, SPRING_BOOT, AnsiColor.DEFAULT, padding.toString(),
                AnsiStyle.FAINT, version));
        printStream.println();
    }
}
