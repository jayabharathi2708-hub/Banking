
package com.example.bankingsystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/auth")
public class AuthController {

    // Temporary in-memory users (later replace with DB)
    private Map<String, String> users = new HashMap<>();

    // Show Login Page
    @GetMapping("/login")
    public String loginPage() {
        return "login";   // templates/login.html
    }

    // Process Login
    @PostMapping("/login")
    public String loginSubmit(@RequestParam String username,
                              @RequestParam String password,
                              Model model) {

        if (users.containsKey(username) && users.get(username).equals(password)) {
            model.addAttribute("username", username);
            return "dashboard";   // templates/dashboard.html
        } else {
            model.addAttribute("error", "Invalid username or password!");
            return "login";
        }
    }

    // Show Register Page
    @GetMapping("/register")
    public String registerPage() {
        return "register";   // templates/register.html
    }

    // Process Register
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

    // Dashboard
    @GetMapping("/dashboard")
    public String dashboard() {
        return "dashboard"; // templates/dashboard.html
    }

    // Logout
    @GetMapping("/logout")
    public String logout() {
        return "redirect:/auth/login";
    }
}


