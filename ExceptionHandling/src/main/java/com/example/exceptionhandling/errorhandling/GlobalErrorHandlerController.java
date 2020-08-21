package com.example.exceptionhandling.errorhandling;

import com.example.exceptionhandling.dtos.ApiError;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@Order
@ControllerAdvice
public class GlobalErrorHandlerController {
    /**
     * Global exception handler for all the Controllers.
     * <p>
     * {@link ExceptionHandler} shows us which Exceptions are handled by the method.
     * The method argument must match the caught exception, or be its parent, or
     * the Exception Handler will not catch it and throw the default exception.
     *
     * @param ex type of exceptions to be caught
     * @return - response entity
     * @throws Exception - the exception
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiError> handleException(Exception ex) throws Exception {
        /**
         * If I have any exceptions that are annotated with {@link ResponseStatus},
         * this will throw them to the client as they are locally, instead of overriding them
         * with the Global Handler.
         *
         * Note: It is meaningless to have a list of exceptions in {@link ExceptionHandler}
         * if the parameter of the method is not their parent
         *
         * If I do not re-throw it, it will be overridden by the Global Handler.
         */
        if (ex.getClass().isAnnotationPresent(ResponseStatus.class)) {
            throw ex;
        }

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        ApiError apiError =
                new ApiError(HttpStatus.I_AM_A_TEAPOT, ex.getMessage(),"Whoops, something went wrong");
        return new ResponseEntity<>(apiError, headers, apiError.getStatus());
    }

}
