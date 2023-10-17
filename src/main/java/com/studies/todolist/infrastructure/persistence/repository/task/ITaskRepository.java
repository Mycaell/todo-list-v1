package com.studies.todolist.infrastructure.persistence.repository.task;

import com.studies.todolist.domain.task.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ITaskRepository extends JpaRepository<Task, UUID> {
    Optional<List<Task>> findAllByUserId(UUID userId);
}
