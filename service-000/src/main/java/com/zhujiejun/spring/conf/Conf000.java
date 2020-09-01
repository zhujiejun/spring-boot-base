package com.zhujiejun.spring.conf;

import com.zhujiejun.spring.bean.Hello;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

@Configuration
@Import(Hello.class)
@PropertySource({"classpath:autoconfig.yml"})
public class Conf000 {

}
