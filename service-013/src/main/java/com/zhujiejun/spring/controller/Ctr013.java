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
        List<String> greetings = Arrays.asList("Hi there", "Greetings", "Salutations");
        Random rand = new Random();
        int randomNum = rand.nextInt(greetings.size());
        return String.format("%s: %s", prefix, greetings.get(randomNum));
    }
}
