package com.zhujiejun.spring.proc;

import com.zhujiejun.spring.bean.Hello;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Objects;

@Slf4j
@Component
public class Proc000 implements BeanFactoryPostProcessor {
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        log.warn("\n---------the current IOC container type is {}.----------\n", beanFactory.getClass());
        int beanDefinitionCount = beanFactory.getBeanDefinitionCount();
        String[] beanDefinitionNames = beanFactory.getBeanDefinitionNames();
        log.warn("---------the current IOC container has {} beans.----------\n", beanDefinitionCount);
        Arrays.stream(beanDefinitionNames).forEach(name -> log.warn("---------the current IOC bean name is {}.----------\n", name));

        /*TaskProperties taskProperties = beanFactory.getBean("spring.cloud.task-org.springframework.cloud.task.configuration.TaskProperties",
                TaskProperties.class);
        log.warn("---------the tablePrefix of bean taskProperties in current IOC container is {}.----------\n",
                taskProperties.getTablePrefix());*/

        Hello hello = beanFactory.getBean("com.zhujiejun.spring.bean.Hello", Hello.class);
        log.warn("---------the bean hello in current IOC container is {}.----------\n", hello.toString());
        if (Objects.nonNull(hello)) {
            log.warn("---------the username of bean hello in current IOC container is {}.----------\n", hello.getUsername());
        }
    }
}
