package com.example.touragency.repositories;

import com.example.touragency.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

/*
Jpa репозиторий
 */
public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}
