package com.zhujiejun.spring.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/msg")
public class Con005 {
    @RequestMapping("/hello")
    public String show() {
        return "World!\n";
    }
}