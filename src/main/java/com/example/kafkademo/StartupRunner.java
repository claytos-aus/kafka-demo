package com.example.kafkademo;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.SuccessCallback;

import example.avro.User;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class StartupRunner implements CommandLineRunner {
	
	@Autowired
	private KafkaTemplate<String,User> kafkaTemplate;

	User user;

	@Value("${kafka.topic}")
	private String topic;

	@Override
	public void run(String... args) throws Exception {
		// Map<String,String> m = new HashMap<String,String>();
		// m.put("a","1");
		user = new User("test user", 3, "blue");
		kafkaTemplate.send(topic,user).addCallback(
			result -> log.info("sent data"), 
			exception -> log.error("error occurred"));
	}

}
