package com.example.kafkademo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import example.avro.User;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class PublisherRunner implements CommandLineRunner {
	
	@Autowired
	private KafkaTemplate<String,User> kafkaTemplate;

	User user;

	@Value("${kafka.topic}")
	private String topic;

	@Override
	public void run(String... args) throws Exception {
		user = new User("test user", 3, "blue");
		kafkaTemplate.send(topic,user).addCallback(
			result -> log.info("sent data"), 
			exception -> log.error("error occurred"));
	}

}
