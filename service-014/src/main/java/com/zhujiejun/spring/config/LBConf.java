package com.zhujiejun.spring.config;

import org.springframework.cloud.client.DefaultServiceInstance;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.loadbalancer.core.ServiceInstanceListSupplier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import reactor.core.publisher.Flux;

import java.util.Arrays;
import java.util.List;

@Configuration
public class LBConf {
    @Bean
    @Primary
    ServiceInstanceListSupplier serviceInstanceListSupplier() {
        return new DefaultServiceInstanceListSuppler("service-013");
    }
}

class DefaultServiceInstanceListSuppler implements ServiceInstanceListSupplier {

    private final String serviceId;

    DefaultServiceInstanceListSuppler(String serviceId) {
        this.serviceId = serviceId;
    }

    @Override
    public String getServiceId() {
        return serviceId;
    }

    @Override
    public Flux<List<ServiceInstance>> get() {
        return Flux.just(Arrays.asList(
                new DefaultServiceInstance(serviceId + "1", serviceId, "localhost", 12013, false),
                new DefaultServiceInstance(serviceId + "2", serviceId, "localhost", 22013, false),
                new DefaultServiceInstance(serviceId + "3", serviceId, "localhost", 32013, false)));
    }
}
