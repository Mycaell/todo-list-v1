package com.studies.todolist.application.user.service;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.studies.todolist.domain.shared.exception.ResourceNotFoundException;
import com.studies.todolist.domain.user.model.User;
import com.studies.todolist.domain.user.service.IUserService;
import com.studies.todolist.infrastructure.persistence.repository.user.IUserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements IUserService {

    private final IUserRepository userRepository;

    public UserServiceImpl(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User create(User user) {
        String encodedPassword = encodePassword(user.getPassword());

        user.setPassword(encodedPassword);

        return this.userRepository.save(user);
    }

    @Override
    public User update(UUID uuid, User object) {
        return null;
    }

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public User findById(UUID uuid) {
        return userRepository.findById(uuid).orElseThrow(() -> new ResourceNotFoundException("User"));
    }

    @Override
    public void delete(UUID uuid) {

    }


    private String encodePassword(String password) {
        return BCrypt.withDefaults()
                .hashToString(12, password.toCharArray());
    }
}
