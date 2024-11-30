package org.nastya.backend.controller;

import lombok.RequiredArgsConstructor;
import org.nastya.backend.dto.TaskRequest;
import org.nastya.backend.exception.TaskAlreadyExistsException;
import org.nastya.backend.model.Task;
import org.nastya.backend.security.CustomUserDetails;
import org.nastya.backend.service.TasksService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/tasks")
@RequiredArgsConstructor
public class TasksController {

    private final TasksService tasksService;

    @PostMapping(consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Map<String, String>> add(@RequestBody TaskRequest taskRequest){
        try{
            tasksService.addTask(taskRequest);
            return ResponseEntity.ok(Map.of("message", "The task was successfully created"));
        } catch (TaskAlreadyExistsException e){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(Map.of("message", e.getMessage()));
        }
    }

    @GetMapping
    public ResponseEntity<List<Task>> getAll(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        var principal = (CustomUserDetails) authentication.getPrincipal();
        List<Task> tasks = tasksService.getAllTask(principal.getId());
        return ResponseEntity.ok(tasks);
    }

    @PatchMapping(consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Map<String, String>> update(@RequestBody TaskRequest taskRequest){
        tasksService.updateTask(taskRequest);
        return ResponseEntity.ok(Map.of("message", "The task was successfully updated"));
    }


}
