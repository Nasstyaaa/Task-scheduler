package org.nastya.emailsender.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.nastya.emailsender.DTO.Message;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class KafkaConsumer {

    @KafkaListener(topics = "EMAIL_SENDING_TASKS", groupId = "email_consumer")
    public void listen(String receivedMessage) {
        try {
            Message message = new ObjectMapper().readValue(receivedMessage, Message.class);
            log.info("The message '{}' was successfully received from another microservice", message.getHeader());
        } catch (JsonProcessingException e) {
            log.error("Error serializing Kafka message", e);
            throw new RuntimeException("Failed to serialize Kafka message", e);
        }
    }

}
