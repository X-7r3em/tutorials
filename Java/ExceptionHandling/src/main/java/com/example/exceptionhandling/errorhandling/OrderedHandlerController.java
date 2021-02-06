package com.example.exceptionhandling.errorhandling;

import com.example.exceptionhandling.dtos.ApiError;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Order(5)
@ControllerAdvice
public class OrderedHandlerController {
    /**
     * Custom exception handler for the specified exceptions.
     * <p>
     * {@link ExceptionHandler} shows us which Exceptions are handled by the method.
     * The method argument must match the caught exception, or be its parent, or
     * the Exception Handler will not catch it and throw the default exception.
     *
     * Note: It is meaningless to have a list of exceptions in {@link ExceptionHandler}
     * if the parameter of the method is not their parent
     *
     * @param ex type of exceptions to be caught
     * @return - response entity
     */
    @ExceptionHandler({IndexOutOfBoundsException.class, IllegalArgumentException.class})
    public ResponseEntity<ApiError> handleException(RuntimeException ex) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        ApiError apiError =
                new ApiError(HttpStatus.I_AM_A_TEAPOT, ex.getMessage(),"This is the ORDER 5 handler");
        return new ResponseEntity<>(apiError, headers, apiError.getStatus());
    }
}
