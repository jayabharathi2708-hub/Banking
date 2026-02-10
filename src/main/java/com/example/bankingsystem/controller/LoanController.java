package com.example.bankingsystem.controller;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.bankingsystem.entity.Loan;
import com.example.bankingsystem.service.AccountService.LoanService;

@RestController
@RequestMapping("/loans")
public class LoanController {

    @Autowired
    private LoanService loanService;

    @PostMapping("/apply/{userId}")
    public Loan applyLoan(@PathVariable Long userId,
                          @RequestParam String type,
                          @RequestParam double amount) {
        return loanService.applyLoan(userId, type, amount);
    }

    @GetMapping("/{userId}")
    public Optional<Loan> getLoans(@PathVariable Long userId) {
        return loanService.getLoans(userId);
    }
}
