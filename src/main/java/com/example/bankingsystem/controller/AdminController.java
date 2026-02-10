package com.example.bankingsystem.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.bankingsystem.entity.Account;
import com.example.bankingsystem.entity.Loan;
import com.example.bankingsystem.entity.User;
import com.example.bankingsystem.entity.Transaction;
import com.example.bankingsystem.repository.AccountRepository;
import com.example.bankingsystem.repository.LoanRepository;
import com.example.bankingsystem.repository.UserRepository;
import com.example.bankingsystem.repository.TransactionRepository;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private AccountRepository accountRepo;

    @Autowired
    private TransactionRepository transactionRepo;

    @Autowired
    private LoanRepository loanRepo;

    @GetMapping("/customers")
    public List<User> getAllCustomers() {
        return userRepo.findAll();
    }

    @GetMapping("/accounts")
    public List<Account> getAllAccounts() {
        return accountRepo.findAll();
    }

    @GetMapping("/transactions")
    public List<Transaction> getAllTransactions() {
        return transactionRepo.findAll();
    }

    @GetMapping("/loans")
    public List<Loan> getAllLoans() {
        return loanRepo.findAll();
    }
}
