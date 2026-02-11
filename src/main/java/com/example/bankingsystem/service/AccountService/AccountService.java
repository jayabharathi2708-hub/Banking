package com.example.bankingsystem.service.AccountService;


import java.util.Optional;

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

    public Optional<Account> getAccountByUserId(Long userId) {
        return Optional.ofNullable(accountRepository.findByUserId(userId));
    }

    public Account deposit(long accountId, double amount) {
        Account account = accountRepository.findByUserId(accountId);
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

    public Object getBalance(Long userId) {
        System.out.println("Fetching balance for user ID: " + userId);
        Account account = accountRepository.findByUserId(userId);
        if (account == null) {
            throw new RuntimeException("Account not found for user ID: " + userId);
        }
        return account.getBalance();
    }
}
