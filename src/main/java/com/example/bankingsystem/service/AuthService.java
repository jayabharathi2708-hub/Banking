package com.example.bankingsystem.service;

import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.bankingsystem.entity.Account;
import com.example.bankingsystem.entity.User;
import com.example.bankingsystem.repository.UserRepository;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private AccountService accountService;

    // Register user
    public void register(User user2) {
        User user = new User();
        user.setUsername(user2.getUsername());
        user.setEmail(user2.getEmail());
        user.setPassword(user2.getPassword());
        user.setAccountNumber(new Random().nextInt(100000) + "");

        // Save user first so it has an id, then create and save account
        User savedUser = userRepo.save(user);

        Account account = new Account();
        account.setBalance(0.0);
        account.setUser(savedUser);
        account.setAccountNumber(savedUser.getAccountNumber());

        accountService.saveAccount(account);
    }

    // Login user
    public User login(String username, String password) {
        Optional<User> user = userRepo.findByUsername(username);

        if (!user.isPresent() || !user.get().getPassword().equals(password)) {
            throw new RuntimeException("Invalid Login");
        }

        return user.get();
    }


    public boolean findByUsername(String username) {
        return userRepo.findByUsername(username).isPresent();
    }

    public User getUserByUsername(String username) {
        return userRepo.findByUsername(username)
                .orElse(null);
    }


}
