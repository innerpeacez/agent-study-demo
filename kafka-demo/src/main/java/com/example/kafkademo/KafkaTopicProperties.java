package com.example.kafkademo;

import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.common.config.TopicConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.config.TopicBuilder;

import java.io.Serializable;
import java.util.Arrays;

public class KafkaTopicProperties implements Serializable {
    // Springboot 默认会创建 KafkaAdmin
//    @Bean
//    public KafkaAdmin admin() {
//        Map<String, Object> configs = new HashMap<>();
//        configs.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");
//        return new KafkaAdmin(configs);
//    }

    @Bean
    public NewTopic topic1() {
        return TopicBuilder.name("thing1")
                .partitions(10)
                .replicas(3)
                .compact()
                .build();
    }

    @Bean
    public NewTopic topic2() {
        return TopicBuilder.name("thing2")
                .partitions(10)
                .replicas(3)
                .config(TopicConfig.COMPRESSION_TYPE_CONFIG, "zstd")
                .build();
    }

    @Bean
    public NewTopic topic3() {
        return TopicBuilder.name("thing3")
                .assignReplicas(0, Arrays.asList(0, 1))
                .assignReplicas(1, Arrays.asList(1, 2))
                .assignReplicas(2, Arrays.asList(2, 0))
                .config(TopicConfig.COMPRESSION_TYPE_CONFIG, "zstd")
                .build();
    }

    @Bean
    public NewTopic topic4() {
        return TopicBuilder.name("defaultBoth")
                .build();
    }

    @Bean
    public NewTopic topic5() {
        return TopicBuilder.name("defaultPart")
                .replicas(1)
                .build();
    }

    @Bean
    public NewTopic topic6() {
        return TopicBuilder.name("defaultRepl")
                .partitions(3)
                .build();
    }
}