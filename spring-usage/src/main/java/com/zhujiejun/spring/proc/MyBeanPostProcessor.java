package com.zhujiejun.spring.proc;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

@Component
public class MyBeanPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("在Bean初始化之前执行BeanPostProcessor的方法, beanName=" + beanName + ", beanType=" + bean.getClass());
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("在Bean初始化之后执行BeanPostProcessor的方法, beanName=" + beanName + ", beanType=" + bean.getClass());
        return bean;
    }
}
