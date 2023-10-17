package com.studies.todolist.infrastructure.persistence.repository.user;

import com.studies.todolist.domain.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface IUserRepository extends JpaRepository<User, UUID> {

    Optional<User> findByUsername(String username);

}
