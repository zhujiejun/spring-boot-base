package com.zhujiejun.spring.bean;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "sfb.tim")
public class Hello {

    private String msg;
    private String username;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "Hello{" +
                "msg='" + msg + '\'' +
                ", username='" + username + '\'' +
                '}';
    }
}
