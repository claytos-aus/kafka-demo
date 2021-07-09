package com.example.kafkademo;

import java.util.Random;

import org.apache.kafka.clients.producer.RecordMetadata;
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
		Random rand = new Random();
		user = new User("test user", rand.nextInt(10) , "blue");
		kafkaTemplate.send(topic,user).addCallback(
			result -> {
				final RecordMetadata m = result.getRecordMetadata();
				log.info("sent data to topic [{}] partition [{}] @offset [{}]",
					m.topic(),
					m.partition(),
					m.offset());
			}, 
			exception -> log.error("error occurred: ",exception)
			);
	}

}
