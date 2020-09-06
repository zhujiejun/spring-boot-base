package com.zhujiejun.spring.conf;

import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Produced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafkaStreams;
import org.springframework.kafka.support.serializer.JsonSerde;

@Configuration
@EnableKafkaStreams
public class Conf010 {
    @Bean
    public KStream<Integer, String> kafkaStream(StreamsBuilder streamsBuilder) {
        KStream<Integer, String> stream = streamsBuilder.stream("ks-1-In");
        stream.map((k, v) -> new KeyValue<>(k, v.toUpperCase())).to("ks-1-Out", Produced.with(Serdes.Integer(), new JsonSerde<>()));
        return stream;
    }
}
