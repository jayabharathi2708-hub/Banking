package com.example.bankingsystem.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.bankingsystem.entity.Account;
import com.example.bankingsystem.entity.Transaction;
import com.example.bankingsystem.repository.AccountRepository;
import com.example.bankingsystem.repository.TransactionRepository;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepo;

    @Autowired
    private AccountRepository accountRepo;

    // Get all transactions by accountId
    public List<Transaction> getTransactionsByAccountId(Long accountId) {
        return transactionRepo.findByAccountId(accountId);
    }

    // Save transaction (for deposit / withdraw)
    public Transaction createTransaction(Long accountId, double amount, String type) {
        Account acc = accountRepo.findById(accountId)
                .orElseThrow(() -> new RuntimeException("Account not found"));

        Transaction tx = new Transaction();
        tx.setAccount(acc);
        tx.setAmount(amount);
        tx.setType(type);
        tx.setDate(LocalDateTime.now());

        return transactionRepo.save(tx);
    }
}
