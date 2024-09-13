package com.example.final_project.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class TransactionRequestDTO {
    private Integer type;
    private Integer fromAccount;
    private Integer fromAccountSortCode;
    private Integer toAccount;
    private Integer toAccountSortCode;
    private Double amount;

    public TransactionRequestDTO(Integer type, Integer fromAccount, Integer fromAccountSortCode, Integer toAccount, Integer toAccountSortCode, Double amount) {
        this.type = type;
        this.fromAccount = fromAccount;
        this.fromAccountSortCode = fromAccountSortCode;
        this.toAccount = toAccount;
        this.toAccountSortCode = toAccountSortCode;
        this.amount = amount;
    }

    public TransactionRequestDTO() {

    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getFromAccount() {
        return fromAccount;
    }

    public void setFromAccount(Integer fromAccount) {
        this.fromAccount = fromAccount;
    }

    public Integer getFromAccountSortCode() {
        return fromAccountSortCode;
    }

    public void setFromAccountSortCode(Integer fromAccountSortCode) {
        this.fromAccountSortCode = fromAccountSortCode;
    }

    public Integer getToAccount() {
        return toAccount;
    }

    public void setToAccount(Integer toAccount) {
        this.toAccount = toAccount;
    }

    public Integer getToAccountSortCode() {
        return toAccountSortCode;
    }

    public void setToAccountSortCode(Integer toAccountSortCode) {
        this.toAccountSortCode = toAccountSortCode;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}
