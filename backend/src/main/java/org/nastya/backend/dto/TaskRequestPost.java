package org.nastya.backend.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class TaskRequestPost {
    private String header;
    private String text;
    private Integer userId;
}
