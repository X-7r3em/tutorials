package com.example.exceptionhandling.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.I_AM_A_TEAPOT)
public class CustomException extends RuntimeException {
    public CustomException(String message) {
        super(message);
    }
}
