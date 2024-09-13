package com.example.final_project.repository;

import com.example.final_project.entities.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    void deleteByFromAccountNumber(Integer accountNumber);
    void deleteByToAccountNumber(Integer accountNumber);
}

