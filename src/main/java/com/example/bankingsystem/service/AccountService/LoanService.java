package com.example.bankingsystem.service.AccountService;



import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.bankingsystem.entity.Loan;
import com.example.bankingsystem.entity.User;
import com.example.bankingsystem.repository.LoanRepository;
import com.example.bankingsystem.repository.UserRepository;





@Service
public class LoanService {

    @Autowired
    private LoanRepository loanRepo;

    @Autowired
    private UserRepository userRepo;

    public Loan applyLoan(Long userId, String type, double amount) {
        User user = userRepo.findById(userId).orElseThrow();

        Loan loan = new Loan();
        loan.setType(type);
        loan.setAmount(amount);
        loan.setStatus("Pending");
        loan.setUser(user);

        return loanRepo.save(loan);
    }

    public Optional<Loan> getLoans(Long userId) {
        return loanRepo.findById(userId);
    }
}

