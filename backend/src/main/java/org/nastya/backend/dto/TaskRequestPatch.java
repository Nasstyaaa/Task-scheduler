package org.nastya.backend.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class TaskRequestPatch {
    private Integer id;
    private String header;
    private String text;
    private Integer userId;
    private Boolean isDone;
    private LocalDateTime time;
}
