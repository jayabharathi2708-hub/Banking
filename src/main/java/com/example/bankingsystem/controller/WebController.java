package com.example.bankingsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.bankingsystem.entity.Account;
import com.example.bankingsystem.entity.User;
import com.example.bankingsystem.service.AccountService.AccountService;
import com.example.bankingsystem.service.AccountService.AuthService;

@Controller
@RequestMapping("/mybank")   // ✅ very important
public class WebController {


    @Autowired
    AuthService authService;

    @Autowired
    AccountService accountService;

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

        accountService.deposit(authService.getUserByUsername(username).getId(), amount);
        model.addAttribute("success", "Transaction successful!");
        model.addAttribute("balance", balance);
        model.addAttribute("username", username);
        return "transaction";
    }

    @GetMapping("/loans")
    public String loansPage(@RequestParam String username, Model model) {
        model.addAttribute("username", username);
        return "loans";
    }

    @PostMapping("/loans")
    public String loanSubmit(@RequestParam String username,
                             @RequestParam double amount,
                             Model model) {

        model.addAttribute("success", "Loan of ₹" + amount + " approved for " + username);
        return "loans";
    }
}
