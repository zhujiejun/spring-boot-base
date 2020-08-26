package com.zhujiejun.spring.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/hello")
public class Hello {

    /**
     * app.tim.sdk.app.id=00002
     * app.tim.admin.identifier=admin
     * app.tim.admin.username=zhujiejun
     * app.tim.admin.password=123456789
     *
     * @return
     */
    @Value("${app.tim.sdk.app.id}")
    private String appId;
    @Value("${app.tim.admin.identifier}")
    private String identifier;
    @Value("${app.tim.admin.username}")
    private String username;
    @Value("${app.tim.admin.password}")
    private String password;

    @RequestMapping("/msg")
    public String hello() {
        return "zhujiejun\n";
    }

    @RequestMapping("/{num1}/{num2}")
    public int add(@PathVariable("num1") int num1, @PathVariable("num2") int num2) {
        log.info("----------the result is {}----------", num1 + num2);
        return num1 + num2;
    }

    @RequestMapping("/show")
    public String show() {
        log.info("----------the id indentifier username password are {} {} {} {} ----------", appId, identifier, username, password);
        Map<String, String> map = new HashMap();
        map.put("id", appId);
        map.put("identifier", identifier);
        map.put("username", username);
        map.put("password", password);
        return new JSONObject(map).toString();
    }
}
