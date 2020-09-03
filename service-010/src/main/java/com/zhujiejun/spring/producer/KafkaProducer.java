package com.zhujiejun.spring.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/kafka/producer")
public class KafkaProducer {

    @Autowired
    private KafkaTemplate kafkaTemplate;

    @RequestMapping("/sent/{msg}")
    public void sentMsg(@PathVariable("msg") String msg) {
        kafkaTemplate.send("spring-boot-producer", msg);
    }
}
