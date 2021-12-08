package com.example.kafkademo;

import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.kafka.core.KafkaProducerException;
import org.springframework.kafka.core.KafkaSendCallback;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;

@SpringBootApplication
public class KafkaDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(KafkaDemoApplication.class, args);
    }

    @Bean
    public NewTopic topic() {
        return TopicBuilder.name("topic1")
                .partitions(10)
                .replicas(1)
                .build();
    }

//    @KafkaListener(id = "myId", topics = "topic1")
//    public void listen(String in) {
//        System.out.println(in);
//    }

    @Bean
    public ApplicationRunner runner(KafkaTemplate<String, String> template) {
        return args -> {
            ListenableFuture<SendResult<String, String>> topic1 = template.send("topic1", "test:" + System.currentTimeMillis());

            topic1.addCallback(new KafkaSendCallback<>() {

                @Override
                public void onSuccess(SendResult<String, String> result) {
                    System.out.println("on success: " + result);
                }

                @Override
                public void onFailure(KafkaProducerException ex) {
                    // 获取失败记录，以便可以做重试补偿
                    ProducerRecord<Integer, String> failed = ex.getFailedProducerRecord();

                }
            });
        };
    }

}
