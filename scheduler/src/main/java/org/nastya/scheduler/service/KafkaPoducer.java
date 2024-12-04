package org.nastya.scheduler.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.nastya.scheduler.dto.Message;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class KafkaPoducer {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public void sendMessage(Message message) {
        try {
            String json = new ObjectMapper().writeValueAsString(message);
            kafkaTemplate.send("EMAIL_SENDING_TASKS",json);
            log.info("Kafka message sent: {}", message.getHeader());
        } catch (JsonProcessingException e){
            log.error("Error serializing Kafka message", e);
            throw new RuntimeException("Failed to serialize Kafka message", e);
        }
    }

}
