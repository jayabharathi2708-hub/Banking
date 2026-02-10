package com.example.bankingsystem.repository;

import com.example.bankingsystem.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // Must match User entity field "username"
    Optional<User> findByUsername(String username);
}
