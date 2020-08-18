package com.example.validationexample.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.ACCEPTED)
public class CustomException extends RuntimeException {
    public CustomException(String message) {
        super(message);
    }
}
