package com.example.bankingsystem.service.AccountService;


import java.util.Optional;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.bankingsystem.entity.Account;
import com.example.bankingsystem.repository.AccountRepository;

@Service
public class AccountService {

    private final AccountRepository accountRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    // Implement getAccounts
    public Optional<Account> getAccounts(Long userId) {
        return accountRepository.findById(userId); // Make sure this method exists in AccountRepository
    }

    public Account deposit(Long accountId, double amount) {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new RuntimeException("Account not found"));
        account.setBalance(account.getBalance() + amount);
        return accountRepository.save(account);
    }

    public Account withdraw(Long accountId, double amount) {
        Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new RuntimeException("Account not found"));
        if (account.getBalance() < amount) {
            throw new RuntimeException("Insufficient balance");
        }
        account.setBalance(account.getBalance() - amount);
        return accountRepository.save(account);
    }

    public User register(User user) {
       
        throw new UnsupportedOperationException("Unimplemented method 'register'");
    }

    public User login(String username, String password) {
        
        throw new UnsupportedOperationException("Unimplemented method 'login'");
    }
}
