package com.example.exceptionhandling.errorhandling;

import com.example.exceptionhandling.dtos.ApiError;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
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

    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(
            MissingServletRequestParameterException ex, HttpHeaders headers,
            HttpStatus status, WebRequest request) {
        String error = ex.getParameterName() + " parameter is missing";

        ApiError apiError =
                new ApiError(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), error);
        return new ResponseEntity<>(
                apiError, new HttpHeaders(), apiError.getStatus());
    }

    @ExceptionHandler({ ConstraintViolationException.class })
    public ResponseEntity<Object> handleConstraintViolation(
            ConstraintViolationException ex, WebRequest request) {
        List<String> errors = new ArrayList<>();
        for (ConstraintViolation<?> violation : ex.getConstraintViolations()) {
            errors.add(violation.getRootBeanClass().getName() + " " +
                    violation.getPropertyPath() + ": " + violation.getMessage());
        }

        ApiError apiError =
                new ApiError(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), errors);
        return new ResponseEntity<>(
                apiError, new HttpHeaders(), apiError.getStatus());
    }

    @ExceptionHandler({ MethodArgumentTypeMismatchException.class })
    public ResponseEntity<Object> handleMethodArgumentTypeMismatch(
            MethodArgumentTypeMismatchException ex, WebRequest request) {
        String error =
                ex.getName() + " should be of type " + ex.getRequiredType().getName();

        ApiError apiError =
                new ApiError(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), error);
        return new ResponseEntity<Object>(
                apiError, new HttpHeaders(), apiError.getStatus());
    }

    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(
            NoHandlerFoundException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String error = "No handler found  for " + ex.getHttpMethod() + " " + ex.getRequestURL();

        ApiError apiError = new ApiError(HttpStatus.NOT_FOUND, ex.getLocalizedMessage(), error);
        return new ResponseEntity<>(apiError, new HttpHeaders(), apiError.getStatus());
    }
}
