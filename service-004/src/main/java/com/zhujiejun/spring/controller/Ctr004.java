package com.zhujiejun.spring.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/service-004")
public class Ctr004 {
    @RequestMapping("/msg")
    public String msg() {
        return "Hello, World!\n";
    }
}
