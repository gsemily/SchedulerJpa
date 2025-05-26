package com.example.schedulerjpa.repository;

import com.example.schedulerjpa.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

// JPA Repository를 상속하는 UserRepository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
