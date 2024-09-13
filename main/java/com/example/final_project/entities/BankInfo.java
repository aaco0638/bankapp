package com.example.final_project.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "bank_info", schema = "banking")
public class BankInfo {

    // Getters and setters
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "sort_code", nullable = false)
    private String sortCode;

    // Constructor
    public BankInfo() {
    }

    public BankInfo(String sortCode) {
        this.sortCode = sortCode;
    }

}

