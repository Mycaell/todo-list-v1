package com.studies.todolist.application.task.service;

import com.studies.todolist.domain.shared.exception.ResourceNotFoundException;
import com.studies.todolist.domain.shared.exception.ValidationException;
import com.studies.todolist.domain.task.model.Task;
import com.studies.todolist.domain.task.service.ITaskService;
import com.studies.todolist.infrastructure.persistence.repository.task.ITaskRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class TaskServiceImpl implements ITaskService {

    private final ITaskRepository taskRepository;


    public TaskServiceImpl(ITaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public Task create(Task task) {

        validate(task);

        return this.taskRepository.save(task);
    }

    @Override
    public Task update(UUID uuid, Task object) {
        Task savedTask = taskRepository.findById(uuid)
                .orElseThrow(() -> new ResourceNotFoundException("Task"));


        validate(object);

        BeanUtils.copyProperties(object, savedTask, "id", "createdAt", "user");

        return taskRepository.save(savedTask);
    }

    @Override
    public List<Task> findAll() {
        return null;
    }

    @Override
    public Task findById(UUID uuid) {
        return null;
    }

    @Override
    public void delete(UUID uuid) {

    }


    @Override
    public List<Task> findAllByUser(UUID user) {
        return this.taskRepository.findAllByUserId(user).orElse(new ArrayList<Task>());
    }


    private void validate(Task task) {
        LocalDateTime currentDate = LocalDateTime.now();

        if (currentDate.isAfter(task.getStartAt()) || currentDate.isAfter(task.getEndAt())) {
            throw new ValidationException("A data de início / data de término deve ser maior do que a data atual");
        }

        if (task.getStartAt().isAfter(task.getEndAt())) {
            throw new ValidationException("A data de início deve ser menor que a data de término");
        }
    }

}
