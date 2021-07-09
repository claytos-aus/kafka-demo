package com.example.kafkademo;

import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.converter.RecordMessageConverter;
import org.springframework.kafka.support.converter.StringJsonMessageConverter;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumer {

    @Bean
	public RecordMessageConverter converter() {
		return new StringJsonMessageConverter();
	}
    
    @KafkaListener(topics = "${kafka.topic}", groupId = "consumer-group")
    public void consume(@Payload Map<String,Object> m) {
        System.out.println("received:" + m.toString());
    }
}
