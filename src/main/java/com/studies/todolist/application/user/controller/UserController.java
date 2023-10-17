package com.studies.todolist.application.user.controller;

import com.studies.todolist.domain.task.model.Task;
import com.studies.todolist.domain.user.model.User;
import com.studies.todolist.domain.user.service.IUserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private final IUserService userService;

    public UserController(IUserService userService) {
        this.userService = userService;
    }


//    TODO: change entity to DTO
    @PostMapping
    public ResponseEntity create(@RequestBody User user) {
        User savedUser = this.userService.create(user);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(savedUser);
    }


}

