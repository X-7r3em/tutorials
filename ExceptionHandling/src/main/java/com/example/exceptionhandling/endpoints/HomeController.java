package com.example.exceptionhandling.endpoints;

import com.example.exceptionhandling.exceptions.RethrownExceptionFromControllerAdvice;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Validated
public class HomeController {

    @GetMapping("/")
    public String getHome() {
        return "Hello";
    }

    // Rethrow after Controller Advice example
    @GetMapping("/bypass")
    public String getExceptionToBypassControllerAdvice() {
        throw new RethrownExceptionFromControllerAdvice("This is my Custom Exception.");
    }

    // Simple @ExceptionHandler example for its arguments
    @GetMapping("/exrun")
    public String getRuntimeException() {
        throw new NullPointerException("Some other shit !!");
    }

    @GetMapping("/excheck")
    public String getCheckedException() throws Exception {
        throw new Exception("This is Local Checked Exception.");
    }

    // For throwing MethodArgumentTypeMismatchException
    @GetMapping("/type/mismatch")
    public void getTypeMismatch(@RequestParam long id) throws Exception {
    }


}