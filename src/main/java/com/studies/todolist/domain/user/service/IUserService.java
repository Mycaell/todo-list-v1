package com.studies.todolist.domain.user.service;

import com.studies.todolist.domain.shared.service.ICrudService;
import com.studies.todolist.domain.user.model.User;

import java.util.UUID;

public interface IUserService extends ICrudService<User, UUID> {
}
