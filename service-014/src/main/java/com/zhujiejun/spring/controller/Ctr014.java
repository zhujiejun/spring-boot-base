package com.zhujiejun.spring.controller;

import org.springframework.cloud.client.loadbalancer.reactive.ReactorLoadBalancerExchangeFilterFunction;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/service-014")
public class Ctr014 {
    private final WebClient.Builder lbWebClientBuilder;
    private final ReactorLoadBalancerExchangeFilterFunction lbFunction;

    public Ctr014(WebClient.Builder lbWebClientBuilder, ReactorLoadBalancerExchangeFilterFunction lbFunction) {
        this.lbWebClientBuilder = lbWebClientBuilder;
        this.lbFunction = lbFunction;
    }

    @RequestMapping("/hi")
    public Mono<String> hi(@RequestParam(value = "name", defaultValue = "Mary") String name) {
        return lbWebClientBuilder
                .build().get().uri("http://service-013/service/service-013/greeting")
                .retrieve().bodyToMono(String.class)
                .map(greeting -> String.format("%s, %s!\n", greeting, name));
    }

    @RequestMapping("/hello")
    public Mono<String> hello(@RequestParam(value = "name", defaultValue = "John") String name) {
        return WebClient.builder()
                .filter(lbFunction)
                .build().get().uri("http://service-013/service/service-013/greeting")
                .retrieve().bodyToMono(String.class)
                .map(greeting -> String.format("%s, %s!\n", greeting, name));
    }
}
