package com.example.bankingsystem.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.bankingsystem.entity.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {
    List<Account> findByUserUsername(String username);  // find accounts by user's username
    Account findByUserId(Long userId); // find single account by user's id
}
