package com.example.final_project.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CustomerDTO {
    private Long id;
    private String fullName;
    private List<Integer> accounts;

    public CustomerDTO(Long id, String fullName, List<Integer> accounts) {
        this.id = id;
        this.fullName = fullName;
        this.accounts = accounts;
    }
}
