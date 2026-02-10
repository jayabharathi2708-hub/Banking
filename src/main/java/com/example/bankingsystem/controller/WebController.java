package com.example.bankingsystem.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/mybank")   // ✅ very important
public class WebController {

    private Map<String, String> users = new HashMap<>();
    private Map<String, Double> accounts = new HashMap<>();

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

        if (users.containsKey(username) && users.get(username).equals(password)) {
            accounts.putIfAbsent(username, 1000.0);
            model.addAttribute("username", username);
            model.addAttribute("balance", accounts.get(username));
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

        if (users.containsKey(username)) {
            model.addAttribute("error", "Username already exists!");
            return "register";
        }

        users.put(username, password);
        model.addAttribute("success", "Registration successful! Please login.");
        return "login";
    }

    @GetMapping("/accounts")
    public String accountsPage(@RequestParam String username, Model model) {
        model.addAttribute("username", username);
        model.addAttribute("balance", accounts.get(username));
        return "accounts";
    }

    @GetMapping("/transaction")
    public String transactionPage() {
        return "transaction";
    }

    @PostMapping("/transaction")
    public String transactionSubmit(@RequestParam String username,
                                    @RequestParam double amount,
                                    @RequestParam String type,
                                    Model model) {

        double balance = accounts.getOrDefault(username, 0.0);

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

        accounts.put(username, balance);
        model.addAttribute("success", "Transaction successful!");
        model.addAttribute("balance", balance);
        model.addAttribute("username", username);
        return "transaction";
    }

    @GetMapping("/loans")
    public String loansPage() {
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
