package com.zhujiejun.spring.bean;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "sfb.tim")
public class Hello {
    private String username;
    private String password;
}