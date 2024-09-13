package com.example.final_project.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountagainDTO {
    private double balance;
    private String accountType;
    private Integer accountNumber;
    private Integer sortCode;
    private Long accountUserId;

    public AccountagainDTO(Integer accountNumber) {
        //this.balance = balance;
        //this.accountType = accountType;
        this.accountNumber = accountNumber;
        //this.sortCode = sortCode;
        //this.accountUserId = accountUserId;
    }

    public AccountagainDTO() {}
}
