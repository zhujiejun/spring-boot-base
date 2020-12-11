package com.zhujiejun.spring.controller;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/service-015")
@ConfigurationProperties(prefix = "spring.datasource")
public class Ctr015 {
    private String url;
    private String username;
    private String password;
    private String driverClassName;

    @RequestMapping("/get/{key}")
    public String get(@PathVariable("key") String key) {
        return String.format("the data source info is: \n" +
                        "url: %s\n" +
                        "username: %s\n" +
                        "password: %s\n" +
                        "driverClassName: %s\n",
                url, username, password, driverClassName);
    }
}
