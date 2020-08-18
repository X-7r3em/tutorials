package com.example.exceptionHandlingExample;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ErrorHandlerController {
    /**
     * Global exception handler for all the Controllers.
     * <p>
     * {@link ExceptionHandler} shows us which Exceptions are handled by the method.
     * The method argument must match the caught exception, or be its parent, or
     * the Exception Handler will not catch it and throw the default exception.
     *
     * @param ex type of exceptions to be caught
     * @return - response entity
     * @throws Exception
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception ex) throws Exception {
        /**
         * If I have any exceptions that are annotated with {@link ResponseStatus},
         * this will throw them to the client as they are locally, instead of overriding them
         * with the Global Handler.
         *
         * If I do not re-throw it, it will be overriden by the Global Handler.
         */
        if (ex.getClass().isAnnotationPresent(ResponseStatus.class)) {
            throw ex;
        }

        ResponseEntity<String> response = new ResponseEntity<>("This is GLOBAL EXCEPTION: \n" + ex.getMessage(), HttpStatus.I_AM_A_TEAPOT);

        return response;
    }
}
