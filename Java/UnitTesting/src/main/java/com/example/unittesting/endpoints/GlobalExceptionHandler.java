package com.example.unittesting.endpoints;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ApiError> handleIllegalArgumentException(IllegalArgumentException ex) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        ApiError apiError = new ApiError(HttpStatus.I_AM_A_TEAPOT.value(), "I guess I am a teapot");
        return new ResponseEntity<>(apiError, headers, HttpStatus.I_AM_A_TEAPOT);
    }

    public static class ApiError {
        private int errorCode;
        private String message;

        public ApiError() {
        }

        public ApiError(int errorCode, String message) {
            this.errorCode = errorCode;
            this.message = message;
        }

        public int getErrorCode() {
            return errorCode;
        }

        public String getMessage() {
            return message;
        }

        @Override
        public String toString() {
            return "ApiError{" +
                    "errorCode=" + errorCode +
                    ", message='" + message + '\'' +
                    '}';
        }
    }
}
