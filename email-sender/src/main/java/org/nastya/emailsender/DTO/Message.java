package org.nastya.emailsender.DTO;

import lombok.Data;

@Data
public class Message {
    private String email;
    private String header;
    private String text;
}
