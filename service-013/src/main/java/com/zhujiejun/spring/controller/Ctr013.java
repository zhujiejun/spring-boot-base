package com.zhujiejun.spring.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/service-013")
public class Ctr013 {
    @RequestMapping("/msg")
    public String msg() {
        String prefix = System.getProperty("prefix");
        return String.format("%s.Hello,World!\n", prefix);
    }
}
