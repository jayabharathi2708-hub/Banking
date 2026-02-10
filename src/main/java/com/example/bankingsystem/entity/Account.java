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
       
        throw new UnsupportedOperationException("Unimplemented method 'getBalance'");
    }

    public void setBalance(double d) {
      
        throw new UnsupportedOperationException("Unimplemented method 'setBalance'");
    }

    public void setType(String string) {
       
        throw new UnsupportedOperationException("Unimplemented method 'setType'");
    }

    public void setUser(User savedUser) {
      
        throw new UnsupportedOperationException("Unimplemented method 'setUser'");
    }

    // getters and setters
}
