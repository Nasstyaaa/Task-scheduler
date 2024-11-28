package org.nastya.backend.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.nastya.backend.dto.TaskRequest;
import org.nastya.backend.exception.TaskAlreadyExistsException;
import org.nastya.backend.model.Task;
import org.nastya.backend.repository.TaskRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TasksService {

    private final TaskRepository taskRepository;

    public void addTask(TaskRequest taskRequest){
        Task task = new ModelMapper().map(taskRequest, Task.class);

        try {
            taskRepository.save(task);
        } catch (DataIntegrityViolationException e){
            throw new TaskAlreadyExistsException();
        }
    }
}
