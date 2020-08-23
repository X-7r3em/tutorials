package com.example.exceptionhandling.errorhandling;

import com.example.exceptionhandling.dtos.ApiError;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Order(1)
@ControllerAdvice
public class CustomErrorHandler extends ResponseEntityExceptionHandler {
    /**
     * This method handles the {@link MethodArgumentNotValidException} and we define our own Response body for it.
     * @param ex - the exception
     * @param headers - the headers of the response
     * @param status - the response status
     * @param request - the request
     * @return - the Response Entity
     */
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<String> errors = new ArrayList<>();
        List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors()
                .stream()
                .sorted(Comparator.comparingInt(f -> f.getField().length()))
                .collect(Collectors.toList());
        for (FieldError error : fieldErrors) {
            errors.add(error.getField() + ": " + error.getDefaultMessage());
        }

        // ToDo: How do i get in here?!?
        List<ObjectError> objectErrors = ex.getBindingResult().getGlobalErrors()
                .stream()
                .sorted(Comparator.comparingInt(f -> f.getObjectName().length()))
                .collect(Collectors.toList());
        for (ObjectError error : objectErrors) {
            errors.add(error.getObjectName() + ": " + error.getDefaultMessage());
        }

        ApiError apiError =
                new ApiError(HttpStatus.BAD_REQUEST,
                        "This error is due to an invalid Request body and MethodArgumentNotValidException",
                        errors);

        // Status is OK so that I can receive the response in the proxy example
        return super.handleExceptionInternal(ex, apiError,headers, HttpStatus.OK, request);
    }
}
