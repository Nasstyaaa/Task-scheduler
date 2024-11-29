package org.nastya.backend.repository;

import org.nastya.backend.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface TaskRepository  extends JpaRepository<Task, String> {
    Boolean existsTaskByHeader(String header);
    Optional<List<Task>> findAllByUserId(Integer userId);
}