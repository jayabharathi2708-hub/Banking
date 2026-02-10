package com.example.bankingsystem.dto;
public class DepositRequest {

    private Long accountId;
    private double amount;

    // Getter and Setter
    public Long getAccountId() {
        return accountId;
    }
 
    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }
 
    public double getAmount() {
        return amount;
    }
 
    public void setAmount(double amount) {
        this.amount = amount;
    }
}

