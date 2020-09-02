package com.zhujiejun.spring.controller;

import com.zhujiejun.spring.aware.Aware002;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Data
@Slf4j
@RefreshScope
@RestController
@RequestMapping("/hello")
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "app.tim")
public class Hello {

    /**
     * app.tim.sdk.app.id=00001
     * app.tim.admin.identifier=admin
     * app.tim.admin.username=zhujiejun
     * app.tim.admin.password=123456789
     *
     * @return
     */
    /*@Value("${app.tim.sdk.app.id}")
    private String appId;
    @Value("${app.tim.admin.identifier}")
    private String identifier;
    @Value("${app.tim.admin.username}")
    private String username;
    @Value("${app.tim.admin.password}")
    private String password;*/

    private String sdkAppId;
    private String adminIdentifier;
    private String adminUsername;
    private String adminPassword;

    @RequestMapping("/msg")
    public String hello() {
        return "zhujiejun\n";
    }

    @RequestMapping("/bean")
    public String getBean() {
        Arrays.stream(Aware002.getBeanNames()).forEach(log::info);
        return String.format("Current IOC container has %d beans.", Aware002.getBeanCount());
    }

    @RequestMapping("/show")
    public String show() {
        //log.info("1.----------the id, indentifier, username and  password are {}, {}, {}, {} ----------",
        //        appId, identifier, username, password);
        log.info("2.----------the id, indentifier, username and  password are {}, {}, {}, {} ----------",
                sdkAppId, adminIdentifier, adminUsername, adminPassword);
        Map<String, String> map = new HashMap();
        /*map.put("id", appId);
        map.put("identifier", identifier);
        map.put("username", username);
        map.put("password", password);*/
        map.put("id", sdkAppId);
        map.put("identifier", adminIdentifier);
        map.put("username", adminUsername);
        map.put("password", adminPassword);
        return map.toString();
    }

    @RequestMapping("/{num1}/{num2}")
    public int add(@PathVariable("num1") int num1, @PathVariable("num2") int num2) {
        log.info("----------the result is {}----------", num1 + num2);
        return num1 + num2;
    }
}
