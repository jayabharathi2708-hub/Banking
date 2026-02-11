package com.example.bankingsystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.bankingsystem.entity.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {
    List<Account> findByUserId(String username);  // This is required for getAccounts
    Account findByUserId(Long userId); // This is required for getBalance
}
