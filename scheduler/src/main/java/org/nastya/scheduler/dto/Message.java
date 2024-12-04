package org.nastya.scheduler.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Message {
    private String email;
    private String header;
    private String text;
}
