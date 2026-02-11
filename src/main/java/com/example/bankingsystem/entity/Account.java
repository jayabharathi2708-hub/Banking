package com.example.bankingsystem.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String type;
    private double balance;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public double getBalance() {
       
        return balance;
    }

    public void setBalance(double d) {
      
        this.balance = d;
    }

    public void setType(String type) {
       
        this.type = type;
    }

    public void setUser(User user) {
      
        this.user = user;
    }
}
