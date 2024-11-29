package org.nastya.backend.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.nastya.backend.dto.TaskRequest;
import org.nastya.backend.exception.TaskAlreadyExistsException;
import org.nastya.backend.model.Task;
import org.nastya.backend.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TasksService {

    private final TaskRepository taskRepository;

    @Transactional
    public void addTask(TaskRequest taskRequest) {
        Task task = new ModelMapper().map(taskRequest, Task.class);
        if (taskRepository.existsTaskByHeader(task.getHeader())) {
            throw new TaskAlreadyExistsException();
        }
        taskRepository.save(task);
    }

    @Transactional
    public List<Task> getAllTask(Integer userId){
        Optional<List<Task>> tasks = taskRepository.findAllByUserId(userId);
        return tasks.orElse(new ArrayList<>());
    }
}
