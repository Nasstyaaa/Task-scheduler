package org.nastya.backend.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class KafkaProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final static String WELCOME_MESSAGE = "Welcome to our service!";
    private final static String WELCOME_HEADER = "Registration Complete";

    public void sendMessage(String email) {
        try {
            Message message = new Message(email, WELCOME_HEADER, WELCOME_MESSAGE);
            String jsonValue = new ObjectMapper().writeValueAsString(message);
            kafkaTemplate.send("EMAIL_SENDING_TASKS", jsonValue);
            log.info("Kafka message sent: {}", jsonValue);
        } catch (JsonProcessingException e) {
            log.error("Error serializing Kafka message", e);
            throw new RuntimeException("Failed to serialize Kafka message", e);
        }
    }

    @AllArgsConstructor
    @Data
    public static class Message {
        private String email;
        private String header;
        private String text;
    }

}
