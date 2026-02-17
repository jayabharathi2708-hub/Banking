package com.example.bankingsystem.controller;

import com.example.bankingsystem.entity.Account;
import com.example.bankingsystem.service.AccountService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    private final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    // Get all accounts of a user
    @GetMapping("/{userId}")
    public Optional<Account> getAccounts(@PathVariable Long userId) {
        return accountService.getAccounts(userId);
    }

    // Deposit money
    @PostMapping("/deposit/{accountId}")
    public Account deposit(@PathVariable Long accountId, @RequestParam double amount) {
        return accountService.deposit(accountId, amount);
    }

    // Withdraw money
    @PostMapping("/withdraw/{accountId}")
    public Account withdraw(@PathVariable Long accountId, @RequestParam double amount) {
        return accountService.withdraw(accountId, amount);
    }
}
