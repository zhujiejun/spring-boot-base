package com.zhujiejun.spring.aware;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class MyApplicationContextAware implements ApplicationContextAware {

    private static AnnotationConfigApplicationContext context;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context = (AnnotationConfigApplicationContext) applicationContext;
    }

    public static Object getObject(String id) {
        return context.getBean(id);
    }

    public static Object getObject(String id, Class<?> clazz) {
        String[] beanDefinitionNames = context.getBeanDefinitionNames();
        int beanDefinitionCount = context.getBeanDefinitionCount();
        System.out.println("beanDefinitionCount = " + beanDefinitionCount);
        Arrays.stream(beanDefinitionNames).forEach(System.out::println);
        return context.getBean(id, clazz);
    }
}
