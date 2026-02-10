package com.example.bankingsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.bankingsystem.entity.Transaction;

public interface TransactionRepository   extends JpaRepository<Transaction,Long> {

}
