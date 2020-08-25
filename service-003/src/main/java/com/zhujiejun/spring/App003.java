package com.zhujiejun.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class App003 {
    public static void main(String[] args) {
        SpringApplication.run(App003.class, args);
    }
}
