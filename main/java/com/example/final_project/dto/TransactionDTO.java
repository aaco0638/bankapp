package com.example.final_project.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class TransactionDTO {
    private Long transactionId;
    private String transactionType;
    private Integer fromAccountNumber;
    private Integer toAccountNumber;
    private Double amount;
    private LocalDateTime timestamp;

    public TransactionDTO() {
    }

    public TransactionDTO(Long transactionId, String transactionType, Integer fromAccountNumber, Integer toAccountNumber, Double amount, LocalDateTime timestamp) {
        this.transactionId = transactionId;
        this.transactionType = transactionType;
        this.fromAccountNumber = fromAccountNumber;
        this.toAccountNumber = toAccountNumber;
        this.amount = amount;
        this.timestamp = timestamp;
    }

}
