package com.example.kafkademo;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author zhaihongwei
 * @since 2021/12/8
 */
@Service
public class KafkaTopicConsumer {

    @KafkaListener(id = "myId", topics = "topic1")
    public void getMessages(List<ConsumerRecord<String, String>> data) {
        System.out.println("----------------: " + data.size() + ":" + data);
    }
}
