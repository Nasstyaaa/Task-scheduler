package org.nastya.backend.repository;

import org.nastya.backend.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TaskRepository  extends JpaRepository<Task, String> {
    Boolean existsTaskByHeader(String header);
}
