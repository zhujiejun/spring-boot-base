package com.zhujiejun.spring.conf;

import com.zhujiejun.spring.proc.Hello;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(Hello.class)
public class Conf001 {
}
