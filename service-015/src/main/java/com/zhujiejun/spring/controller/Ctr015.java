package com.zhujiejun.spring.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/service-015")
public class Ctr015 {
    @Value("${k1}")
    private String v1;
    @Value("${k2}")
    private String v2;
    @Value("${k3}")
    private String v3;

    @RequestMapping("/get/{key}")
    public String get(@PathVariable("key") String key) {
        String v;
        switch (key) {
            case "k2":
                v = v2;
                break;
            case "k3":
                v = v3;
                break;
            default:
                v = v1;
                break;
        }
        return String.format("%s: %s\n", key, v);
    }
}
