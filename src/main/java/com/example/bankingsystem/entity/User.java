package com.example.bankingsystem.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    private int cibilScore;
    private double totalSpend;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Account> accounts = new ArrayList<>();

    public void setCibilScore(int id) {
        
        throw new UnsupportedOperationException("Unimplemented method 'setCibilScore'");
    }

    public void setTotalSpend(int id) {
        
        throw new UnsupportedOperationException("Unimplemented method 'setTotalSpend'");
    }

    public Object getPassword() {
        
        throw new UnsupportedOperationException("Unimplemented method 'getPassword'");
    }

    // getters and setters
}
