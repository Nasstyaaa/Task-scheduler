package org.nastya.emailsender.service;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumer {

    @KafkaListener(topics = "EMAIL_SENDING_TASKS", groupId = "email_consumer")
    public void listen(String message) {
        System.out.println("message from backend: " + message);
    }

}
