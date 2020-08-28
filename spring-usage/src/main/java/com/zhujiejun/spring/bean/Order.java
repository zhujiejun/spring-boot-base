package com.zhujiejun.spring.bean;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component
public class Order {
    public Order() {
        System.out.println("第一步 执行无参数构造创建 bean 实例");
    }

    private String oname;

    public void setOname(String oname) {
        this.oname = oname;
        System.out.println("第二步 调用 set 方法设置属性值");
    }

    //创建执行的初始化的方法
    @PostConstruct
    public void initMethod() {
        System.out.println("第三步 执行初始化的方法");
    }

    //创建执行的销毁的方法
    @PreDestroy
    public void destroyMethod() {
        System.out.println("第五步 执行销毁的方法");
    }
}
