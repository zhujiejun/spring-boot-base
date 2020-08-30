package com.zhujiejun.spring.proc;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Slf4j
@Component
public class Proc001 implements BeanFactoryPostProcessor {
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        int beanDefinitionCount = beanFactory.getBeanDefinitionCount();
        String[] beanDefinitionNames = beanFactory.getBeanDefinitionNames();
        log.info("\n---------the current IOC container has {} beans.----------\n", beanDefinitionCount);
        Arrays.stream(beanDefinitionNames).forEach(name -> log.info("\n---------the current IOC bean name is {}.----------\n", name));
    }
}
