package com.zhujiejun.spring.config;

import com.zhujiejun.spring.resolver.JsonViewResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Conf005 {
    @Bean
    public JsonViewResolver jsonViewResolver() {
        return new JsonViewResolver();
    }
}
