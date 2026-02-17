package com.example.bankingsystem.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.bankingsystem.entity.Loan;
import com.example.bankingsystem.service.LoanService;

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

  
}
