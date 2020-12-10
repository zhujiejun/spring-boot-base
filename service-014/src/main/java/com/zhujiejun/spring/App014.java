package com.zhujiejun.spring;

import com.zhujiejun.spring.config.LoadBalancer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;

@EnableDiscoveryClient
@SpringBootApplication
@LoadBalancerClient(name = "service-014", configuration = LoadBalancer.class)
public class App014 {
    public static void main(String[] args) {
        SpringApplication.run(App014.class, args);
    }
}
