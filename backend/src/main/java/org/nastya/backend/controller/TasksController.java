package org.nastya.backend.controller;

import lombok.RequiredArgsConstructor;
import org.nastya.backend.dto.TaskRequest;
import org.nastya.backend.exception.TaskAlreadyExistsException;
import org.nastya.backend.model.User;
import org.nastya.backend.service.TasksService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/tasks")
@RequiredArgsConstructor
public class TasksController {

    private final TasksService tasksService;

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<?> add(@RequestBody TaskRequest taskRequest){
        try{
            tasksService.addTask(taskRequest);
            return ResponseEntity.status(HttpStatus.OK).body(Map.of("message", "The task was successfully created"));
        } catch (TaskAlreadyExistsException e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(Map.of("message", e.getMessage()));
        }
    }
}
