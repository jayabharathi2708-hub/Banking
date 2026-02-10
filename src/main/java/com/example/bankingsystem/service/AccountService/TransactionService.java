package com.example.bankingsystem.service.AccountService;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.bankingsystem.entity.Transaction;
import com.example.bankingsystem.repository.TransactionRepository;



@Service
public class TransactionService {

    @Autowired
    private TransactionRepository repo;

    public Optional<Transaction> getTransactionsByAccount(Long accountId) {
        return repo.findById(accountId);
    }
}

