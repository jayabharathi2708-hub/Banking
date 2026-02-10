package com.example.bankingsystem.service.AccountService;

import com.example.bankingsystem.entity.Account;
import com.example.bankingsystem.entity.User;
import com.example.bankingsystem.repository.AccountRepository;
import com.example.bankingsystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class AuthService {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private AccountRepository accountRepo;

    // Register new user
    public User register(User user) {
        user.setCibilScore(650 + new Random().nextInt(170));
        user.setTotalSpend(0);

        User savedUser = userRepo.save(user);

        Account acc = new Account();
        acc.setType("Savings");
        acc.setBalance(0);
        acc.setUser(savedUser);
        accountRepo.save(acc);

        return savedUser;
    }

    // Login user
    public User login(String username, String password) {
        User user = userRepo.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!user.getPassword().equals(password)) {
            throw new RuntimeException("Invalid password");
        }

        return user;
    }
}
