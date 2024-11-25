package org.nastya.backend.controller;

import org.nastya.backend.model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TasksController {
    
    @GetMapping
    public ResponseEntity<String> test(){
        return ResponseEntity.ok("hello");
    }
}
