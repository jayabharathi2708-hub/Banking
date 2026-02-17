package com.example.bankingsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.bankingsystem.dto.RegisterRequest;
import com.example.bankingsystem.entity.User;
import com.example.bankingsystem.repository.UserRepository;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepo;

    // Register user
    public void register(RegisterRequest req) {
        User user = new User();
        user.setUsername(req.getUsername());
        user.setEmail(req.getEmail());
        user.setPassword(req.getPassword());

        userRepo.save(user);
    }

    // Login user
    public User login(String username, String password) {
        User user = userRepo.findByUsername(username);

        if (user == null || !user.getPassword().equals(password)) {
            throw new RuntimeException("Invalid Login");
        }

        return user;
    }
}
