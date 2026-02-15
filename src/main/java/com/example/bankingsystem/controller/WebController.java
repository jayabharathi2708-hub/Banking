package com.example.bankingsystem.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.bankingsystem.entity.Account;
import com.example.bankingsystem.entity.User;
import com.example.bankingsystem.entity.Transaction;
import com.example.bankingsystem.service.AccountService.AccountService;
import com.example.bankingsystem.service.AccountService.AuthService;
import com.example.bankingsystem.service.AccountService.LoanService;
import com.example.bankingsystem.service.AccountService.TransactionService;

@Controller
@RequestMapping("/mybank")   // ✅ very important
public class WebController {


    @Autowired
    AuthService authService;

    @Autowired
    AccountService accountService;

    @Autowired
    LoanService loanService;

    @Autowired
    TransactionService transactionService;

    @GetMapping("")
    public String home() {
        return "index";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @PostMapping("/login")
    public String loginSubmit(@RequestParam String username,
                              @RequestParam String password,
                              Model model) {

        if (authService.login(username, password) != null) {
            model.addAttribute("username", username);
            model.addAttribute("balance", accountService.getBalance(authService.getUserByUsername(username).getId()));
            return "accounts";
        } else {
            model.addAttribute("error", "Invalid credentials!");
            return "login";
        }
    }

    @GetMapping("/register")
    public String registerPage() {
        return "register";
    }

    @PostMapping("/register")
    public String registerSubmit(@RequestParam String username,
                                 @RequestParam String password,
                                 Model model) {

        if (authService.findByUsername(username)) {
            model.addAttribute("error", "Username already exists!");
            return "register";
        }
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        authService.register(user);
        
        // users.put(username, password);
        model.addAttribute("success", "Registration successful! Please login.");
        return "login";
    }

    @GetMapping("/accounts")
    public String accountsPage(@RequestParam String username, Model model) {
        model.addAttribute("username", username);
        model.addAttribute("balance", accountService.getBalance(authService.getUserByUsername(username).getId()));
        return "accounts";
    }

    @GetMapping("/transaction")
    public String transactionPage(@RequestParam String username, Model model) {
        model.addAttribute("username", username);
        return "transaction";
    }

    @PostMapping("/transaction")
    public String transactionSubmit(@RequestParam String username,
                                    @RequestParam double amount,
                                    @RequestParam String type,
                                    Model model) {

        Account account = accountService.getAccountByUserId(authService.getUserByUsername(username).getId()).orElse(null);

        double balance = account.getBalance();

        if ("deposit".equals(type)) {
            balance += amount;
        } else if ("withdraw".equals(type)) {
            if (amount > balance) {
                model.addAttribute("error", "Insufficient balance!");
                model.addAttribute("balance", balance);
                model.addAttribute("username", username);
                return "transaction";
            }
            balance -= amount;
        }

        accountService.deposit(authService.getUserByUsername(username).getId(), balance);
        model.addAttribute("success", "Transaction successful!");
        model.addAttribute("balance", balance);
        model.addAttribute("username", username);

        Transaction transaction = new Transaction();
        transaction.setAccount(account);
        transaction.setAmount(amount);
        transaction.setType(type);

        transactionService.saveTransaction(transaction);
        return "transaction";
    }

    @GetMapping("/loans")
    public String loansPage(@RequestParam String username, Model model) {
        model.addAttribute("username", username);
        return "loans";
    }

    @PostMapping("/loans")
    public String loanSubmit(@RequestParam Map<String, String> loanRequest,
                             Model model) {
        String username = (String) loanRequest.get("username");
        String loanType = (String) loanRequest.get("loanType");
        String fullName = (String) loanRequest.get("fullName");
        String email = (String) loanRequest.get("email");
        String phone = (String) loanRequest.get("phone");
        String dob = (String) loanRequest.get("dob");
        String address = (String) loanRequest.get("address");
        double loanAmount = Double.parseDouble(loanRequest.get("loanAmount"));
        int tenure = Integer.parseInt(loanRequest.get("tenure"));
        double income = Double.parseDouble(loanRequest.get("income"));
        int cibilScore = Integer.parseInt(loanRequest.get("cibilScore"));

        User user = authService.getUserByUsername(username);
        if (user == null) {
            model.addAttribute("error", "User not found!");
            return "loans";
        }

        // Validate loan eligibility
        if (cibilScore < 650) {
            model.addAttribute("error", "CIBIL score must be at least 650!");
            return "loans";
        }

        if (income * 0.5 < (loanAmount / (tenure * 12))) {
            model.addAttribute("error", "Loan amount exceeds your repayment capacity!");
            return "loans";
        }

        // Process loan application
        loanService.applyLoan(user.getId(), loanType, loanAmount);

        model.addAttribute("success", "Loan of ₹" + loanAmount + " approved for " + fullName);
        model.addAttribute("username", username);
        return "loans";
    }
}
