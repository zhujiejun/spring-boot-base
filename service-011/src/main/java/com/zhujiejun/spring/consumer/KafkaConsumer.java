package com.zhujiejun.spring.consumer;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class KafkaConsumer {
    @KafkaListener(topics = {"spring-boot-consumer"})
    public void onMsg(ConsumerRecord<?, ?> record) {
        //log.warn("----------current msg is {}-{}-{}----------", record.topic(), record.partition(), record.value());
        log.warn("----------current msg is {}----------", record.value());
    }
}
