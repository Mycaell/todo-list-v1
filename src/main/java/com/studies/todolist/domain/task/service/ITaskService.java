package com.studies.todolist.domain.task.service;

import com.studies.todolist.domain.shared.service.ICrudService;
import com.studies.todolist.domain.task.model.Task;

import java.util.List;
import java.util.UUID;

public interface ITaskService extends ICrudService<Task, UUID> {
    List<Task> findAllByUser(UUID user);
}
