package com.example.bankingsystem.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.bankingsystem.entity.Account;
import com.example.bankingsystem.repository.AccountRepository;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepo;

    // Get all accounts by userId
    public List<Account> getAccountsByUserId(Long userId) {
        return accountRepo.findByUserId(userId);
    }

    // Deposit money
    public Account deposit(Long accountId, double amount) {
        Account acc = accountRepo.findById(accountId)
                .orElseThrow(() -> new RuntimeException("Account not found"));

        acc.setBalance(acc.getBalance() + amount);
        return accountRepo.save(acc);
    }

    // Withdraw money
    public Account withdraw(Long accountId, double amount) {
        Account acc = accountRepo.findById(accountId)
                .orElseThrow(() -> new RuntimeException("Account not found"));

        if (acc.getBalance() < amount) {
            throw new RuntimeException("Insufficient balance");
        }

        acc.setBalance(acc.getBalance() - amount);
        return accountRepo.save(acc);
    }
}
