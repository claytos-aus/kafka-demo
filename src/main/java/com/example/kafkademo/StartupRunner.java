package com.example.kafkademo;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import example.avro.User;

@Component
public class StartupRunner implements CommandLineRunner {
	
	@Autowired
	private KafkaTemplate<String,Object> kafkaTemplate;

	User user;

	@Value("${kafka.topic}")
	private String topic;

	@Override
	public void run(String... args) throws Exception {
		Map<String,String> m = new HashMap<String,String>();
		m.put("a","1");
		kafkaTemplate.send(topic,m);
		System.out.println("published data...");
	}

}
