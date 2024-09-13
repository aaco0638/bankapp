package com.example.final_project.exception;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserErrorResponse {
    private String errorCode;
    private String message;

    public UserErrorResponse(String errorCode, String message) {
        this.errorCode = errorCode;
        this.message = message;
    }

}

