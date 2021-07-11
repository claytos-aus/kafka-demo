package com.example.kafkademo;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

import lombok.Getter;
import lombok.Setter;

@Configuration
@ConfigurationProperties(prefix = "kafka")
@Setter
@Getter
public class TopicConfig {

    String topic;
    int partitions;
    int replicas;

    @Bean
    public NewTopic topicExample() {
        System.out.println(topic + "/" + partitions + "/" + replicas);
        return TopicBuilder.name(topic)
        .partitions(partitions)
        .replicas(replicas)
        .build();
    }
}
