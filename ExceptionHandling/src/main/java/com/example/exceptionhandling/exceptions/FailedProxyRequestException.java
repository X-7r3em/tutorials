package com.example.exceptionhandling.exceptions;

public class FailedProxyRequestException extends RuntimeException {
    public FailedProxyRequestException(String message) {
        super(message);
    }
}
