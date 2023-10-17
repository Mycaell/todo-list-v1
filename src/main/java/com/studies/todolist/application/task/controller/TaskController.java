package com.studies.todolist.application.task.controller;

import com.studies.todolist.domain.task.model.Task;
import com.studies.todolist.domain.task.service.ITaskService;
import com.studies.todolist.domain.user.model.User;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final ITaskService taskService;


    public TaskController(ITaskService taskService) {
        this.taskService = taskService;

    }


    //    TODO: change entity to DTO

    @PostMapping
    public ResponseEntity<Task> create(@RequestBody Task task, HttpServletRequest request) {
        User user = (User) request.getAttribute("user");

        task.setUser(user);

        Task savedTask = this.taskService.create(task);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(savedTask);
    }

    @GetMapping
    public ResponseEntity<List<Task>> findAllByUser(HttpServletRequest request) {
        User user = (User) request.getAttribute("user");

        List<Task> tasks = this.taskService.findAllByUser(user.getId());

        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Task> update(@PathVariable UUID id, @RequestBody Task task, HttpServletRequest request) {
        Task savedTask = taskService.update(id, task);
        return new ResponseEntity<>(savedTask, HttpStatus.OK);
    }

}

