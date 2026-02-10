package com.example.bankingsystem.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "loans")
public class Loan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double amount;
    private int durationMonths;

    @ManyToOne
    @JoinColumn(name = "user_id") // foreign key
    private User user;

    // Constructors
    public Loan() {}

    public Loan(double amount, int durationMonths, User user) {
        this.amount = amount;
        this.durationMonths = durationMonths;
        this.user = user;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public int getDurationMonths() {
        return durationMonths;
    }

    public void setDurationMonths(int durationMonths) {
        this.durationMonths = durationMonths;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setType(String type) {
      
        throw new UnsupportedOperationException("Unimplemented method 'setType'");
    }

    public void setStatus(String string) {
      
        throw new UnsupportedOperationException("Unimplemented method 'setStatus'");
    }
}
