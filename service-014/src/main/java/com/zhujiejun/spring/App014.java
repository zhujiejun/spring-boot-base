package com.zhujiejun.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
//@LoadBalancerClient(name = "service-014", configuration = Conf014.class)
public class App014 {
    public static void main(String[] args) {
        SpringApplication.run(App014.class, args);
    }
}
