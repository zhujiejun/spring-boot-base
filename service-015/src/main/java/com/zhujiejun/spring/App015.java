package com.zhujiejun.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class App015 {
    public static void main(String[] args) {
        SpringApplication.run(App015.class, args);
    }
}
