package com.example.bankingsystem.controller;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.bankingsystem.entity.Transaction;
import com.example.bankingsystem.service.AccountService.TransactionService;



@RestController
@RequestMapping("/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @GetMapping("/{accountId}")
    public Optional<Transaction> getTransactions(@PathVariable Long accountId) {
        return transactionService.getTransactionsByAccount(accountId);
    }
}
