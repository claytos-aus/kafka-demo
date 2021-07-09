package com.example.kafkademo;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import example.avro.User;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class KafkaConsumer {
    
    @KafkaListener(topics = "${kafka.topic}", groupId = "consumer-group")
    public void consume(ConsumerRecord<String,User> m) {
        log.info("received:" + m.value().toString());
    }
}
