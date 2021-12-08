package com.example.kafkademo;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhaihongwei
 * @since 2021/12/8
 */
@RestController
@RequestMapping("/")
public class KafkaController {

    private KafkaTemplate<String, String> template;

    public KafkaController(KafkaTemplate<String, String> template) {
        this.template = template;
    }

    @GetMapping("send")
    public void send() {
//        for (int i = 0; i < 10; i++) {
            new Thread(() -> template.send("topic1", "test:" + System.currentTimeMillis())).start();
//        }
    }
}
