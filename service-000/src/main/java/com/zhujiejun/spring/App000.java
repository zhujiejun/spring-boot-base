package com.zhujiejun.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource({"classpath:autoconfig.properties"})
public class App000 {
    public static void main(String[] args) {
        SpringApplication.run(App000.class, args);
    }
}
