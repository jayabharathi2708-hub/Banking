package com.example.bankingsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.bankingsystem.entity.Loan;

public interface LoanRepository extends JpaRepository<Loan,Long>{

    public Iterable<Loan> findByUserId(Long userId);

}
