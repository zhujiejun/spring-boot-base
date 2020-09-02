package com.zhujiejun.spring.aware;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class Aware002 implements ApplicationContextAware {
    private static ApplicationContext context;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context = applicationContext;
    }

    public static int getBeanCount() {
        return context.getBeanDefinitionCount();
    }

    public static String[] getBeanNames() {
        return context.getBeanDefinitionNames();
    }
}
