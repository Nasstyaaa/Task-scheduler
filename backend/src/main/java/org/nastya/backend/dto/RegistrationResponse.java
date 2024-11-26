package org.nastya.backend.dto;

import lombok.Data;

@Data
public class RegistrationResponse {
    private Integer id;
    private String username;
    private String email;
}
