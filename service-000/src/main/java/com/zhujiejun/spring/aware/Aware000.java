package com.zhujiejun.spring.aware;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class Aware000 implements ApplicationContextAware {

    private static AnnotationConfigApplicationContext context;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context = (AnnotationConfigApplicationContext) applicationContext;
    }

    public static <T> T getTheBean(String beanName, Class<T> clazz) {
        return context.getBean(beanName, clazz);
    }
}
