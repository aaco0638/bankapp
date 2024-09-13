package com.example.final_project.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateAccountRequestDTO {
    private Long customerId;
    private String accountName;
    private Double openingBalance;

    public CreateAccountRequestDTO(String accountName, Long customerId, Double openingBalance) {
        this.accountName = accountName;
        this.customerId = customerId;
        this.openingBalance = openingBalance;
    }
}
