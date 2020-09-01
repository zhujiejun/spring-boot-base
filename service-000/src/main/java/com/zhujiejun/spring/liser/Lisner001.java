package com.zhujiejun.spring.liser;

import com.zhujiejun.spring.aware.Aware000;
import com.zhujiejun.spring.bean.Hello;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.ContextClosedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class Lisner001 {
    @EventListener({ContextClosedEvent.class})
    public void show() {
        Hello hello = Aware000.getTheBean("com.zhujiejun.spring.bean.Hello", Hello.class);
        log.warn("----------the username of current bean hello is {}---------", hello.getUsername());
    }
}
