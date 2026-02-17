package com.example.bankingsystem.controller;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.bankingsystem.entity.Transaction;
import com.example.bankingsystem.service.TransactionService;



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
