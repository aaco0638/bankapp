package com.example.final_project.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterCustomerDto {
    private String name;

    public RegisterCustomerDto(String name) {
        this.name = name;
    }

    public RegisterCustomerDto() {}
}
