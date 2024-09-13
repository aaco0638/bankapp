package com.example.final_project.service;

import com.example.final_project.dto.TransactionDTO;
import com.example.final_project.dto.TransactionRequestDTO;
import com.example.final_project.entities.Transaction;

public interface TransactionService {
    TransactionDTO processTransaction(TransactionRequestDTO request);
    TransactionDTO convertToTransactionDTO(Transaction transaction);
}
