package com.example.bankingsystem.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.bankingsystem.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // Must match User entity field "username"
    Optional<User> findByUsername(String username);
    
}
