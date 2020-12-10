package com.zhujiejun.spring.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

@Slf4j
@RestController
@RequestMapping("/service-013")
public class Ctr013 {
    @RequestMapping("/msg")
    public String msg() {
        String prefix = System.getProperty("prefix");
        return String.format("%s.Hello,World!\n", prefix);
    }

    @GetMapping("/")
    public String home() {
        log.info("Access /");
        return "Hi!";
    }

    @GetMapping("/greeting")
    public String greet() {
        log.info("Access /greeting");
        String prefix = System.getProperty("prefix");
        List<String> grils = Arrays.asList("Wangying", "Pangyao", "Wangpiao", "Yangying");
        List<String> actions = Arrays.asList("love", "hate", "belive", "marry");
        Random rand = new Random();
        int randomNum1 = rand.nextInt(grils.size());
        int randomNum2 = rand.nextInt(actions.size());
        return String.format("%s: %s %s", prefix, grils.get(randomNum1), actions.get(randomNum2));
    }
}
