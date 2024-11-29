package org.nastya.backend.dto;

import lombok.Data;

import java.time.LocalDateTime;
@Data
public class TaskRequest {
    private String header;
    private String text;
    private Integer userId;
    private boolean isDone;
    private LocalDateTime time;
}
