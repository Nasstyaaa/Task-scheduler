package org.nastya.scheduler.repository;

import org.nastya.scheduler.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TaskRepository extends JpaRepository<Task, Integer> {
    Optional<List<Task>> findAllByUserId(Integer userId);
}
