package com.zhujiejun.spring.conf;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {"com.zhujiejun.spring.bean", "com.zhujiejun.spring.proc", "com.zhujiejun.spring.aware"})
public class SpringConf {

}
