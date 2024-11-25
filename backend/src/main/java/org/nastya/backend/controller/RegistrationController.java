package org.nastya.backend.controller;

import lombok.AllArgsConstructor;
import org.nastya.backend.model.User;
import org.nastya.backend.repository.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class RegistrationController {

    private final UserRepository userRepository;

    @GetMapping
    public ResponseEntity<List<User>> test(){
        return ResponseEntity.ok(userRepository.findAll());
    }
}
