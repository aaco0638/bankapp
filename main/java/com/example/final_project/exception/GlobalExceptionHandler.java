package com.example.final_project.exception;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<UserErrorResponse> handleEntityNotFoundException(EntityNotFoundException ex) {
        UserErrorResponse errorResponse = new UserErrorResponse("NOT_FOUND", ex.getMessage());
        logger.error("EntityNotFoundException: {}", ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<UserErrorResponse> handleException(Exception ex) {
        logger.error("An unexpected error occurred", ex);
        UserErrorResponse errorResponse = new UserErrorResponse("INTERNAL_SERVER_ERROR", "An unexpected error occurred. Details: " + ex.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
    }

}
