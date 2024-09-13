package com.example.final_project.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Entity
@Table(name = "transactions", schema = "banking")
@Data
@NoArgsConstructor
public class Transaction {

    //import lombok.Getter;
    //import lombok.Setter;
    //@Getter
    //@Setter

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transaction_id")
    private Long transactionId;

    @Column(name = "from_account_number")
    private Integer fromAccountNumber;

    @Column(name = "to_account_number")
    private Integer toAccountNumber;

    @Column(name = "amount", nullable = false)
    private Double amount;

    @Column(name = "transaction_type", nullable = false)
    private String transactionType;

    @Column(name = "transaction_timestamp", nullable = false)
    private LocalDateTime timestamp = LocalDateTime.now();

    public Transaction(Long transactionId,String transactionType, Integer fromAccountNumber, Integer toAccountNumber, Double amount, LocalDateTime timestamp) {
        this.transactionId = transactionId;
        this.transactionType = transactionType;
        this.fromAccountNumber = fromAccountNumber;
        this.toAccountNumber = toAccountNumber;
        this.amount = amount;
        this.timestamp = timestamp != null ? timestamp : LocalDateTime.now();
    }
}
