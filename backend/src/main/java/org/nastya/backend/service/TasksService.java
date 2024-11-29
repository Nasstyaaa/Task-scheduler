package org.nastya.backend.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.nastya.backend.dto.TaskRequest;
import org.nastya.backend.exception.TaskAlreadyExistsException;
import org.nastya.backend.model.Task;
import org.nastya.backend.repository.TaskRepository;
import org.springframework.stereotype.Service;

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
}
