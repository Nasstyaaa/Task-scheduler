package org.nastya.backend.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.nastya.backend.dto.TaskRequestPatch;
import org.nastya.backend.dto.TaskRequestPost;
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
    public void addTask(TaskRequestPost taskRequestPost) {
        Task task = new ModelMapper().map(taskRequestPost, Task.class);
        task.setId(null);
        task.setIsDone(false);
        taskRepository.saveAndFlush(task);
    }

    @Transactional
    public List<Task> getAllTask(Integer userId){
        Optional<List<Task>> tasks = taskRepository.findAllByUserId(userId);
        return tasks.orElse(new ArrayList<>());
    }

    @Transactional
    public void updateTask(TaskRequestPatch taskRequestPatch) {
        Task task = new ModelMapper().map(taskRequestPatch, Task.class);
        taskRepository.save(task);
    }

    @Transactional
    public void deleteTask(Integer idTask){
        taskRepository.deleteById(String.valueOf(idTask));
    }
}
