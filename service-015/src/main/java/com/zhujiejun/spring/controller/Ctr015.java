package com.zhujiejun.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.core.env.Environment;
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

    @Autowired
    private Environment environment;

    @RequestMapping("/get/{key}")
    public String get(@PathVariable("key") String key) {
        return environment.getProperty(key);
    }

    @RequestMapping("/datasource")
    public String datasource() {
        return String.format("the data source info is: \n" +
                        "url: %s\n" +
                        "driver: %s\n" +
                        "username: %s\n" +
                        "password: %s\n",
                url, driverClassName, username, password);
    }
}
