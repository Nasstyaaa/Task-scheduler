package org.nastya.backend.controller;

import lombok.AllArgsConstructor;
import org.nastya.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class TestController {

    @Autowired
    private final UserRepository userRepository;

    @GetMapping("/test")
    public String test(){
        return userRepository.findAll().toString();
    }
}
