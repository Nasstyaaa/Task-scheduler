package org.nastya.backend.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RegistrationRequest {

    @Size(min = 2, max = 25, message = "Invalid username length. It must be between 2 and 25 characters.")
    private String username;

    @Email(regexp = ".+[@].+[\\.].+", message = "The email address is incorrect.")
    private String email;

    @Size(min = 8, max = 17, message = "The password length must be between 8 and 17 characters.")
    private String password;
}
