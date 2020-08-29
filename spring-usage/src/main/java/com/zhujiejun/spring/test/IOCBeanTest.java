package com.zhujiejun.spring.test;

import com.zhujiejun.spring.aware.MyApplicationContextAware;
import com.zhujiejun.spring.bean.Order;
import com.zhujiejun.spring.conf.SpringConf;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class IOCBeanTest {
    @Test
    public void test001() {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        Order order = context.getBean("order", Order.class);
        System.out.println("第四步 获取创建 bean 实例对象");
        System.out.println(order);
        //手动让 bean 实例销毁
        context.close();
    }

    @Test
    public void test002() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConf.class);
        Order order = context.getBean("order", Order.class);
        //Order order1 = context.getBean("order1", Order.class);
        System.out.println("第四步 获取创建 bean 实例对象");
        System.out.println(order);
        //System.out.println(order1);
        //手动让 bean 实例销毁
        context.close();
    }

    @Test
    public void test003() {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConf.class);
        Object order = MyApplicationContextAware.getObject("order", Order.class);
        System.out.println(order);
        context.close();
    }

    public static void main(String[] args) {
        IOCBeanTest test = new IOCBeanTest();
        //test.test001();
        //test.test002();
        test.test003();
    }
}
