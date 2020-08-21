package com.example.exceptionhandling.endpoints;

import com.example.exceptionhandling.dtos.Car;
import com.example.exceptionhandling.exceptions.RethrownExceptionFromControllerAdvice;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@Validated
public class HomeController {

    @GetMapping("/")
    public String getHome() {
        return "Hello";
    }

    @GetMapping("/bypass")
    public String getExceptionToBypassControllerAdvice() {
        throw new RethrownExceptionFromControllerAdvice("This is my Custom Exception.");
    }

    @GetMapping("/exrun")
    public String getRuntimeException() {
        throw new NullPointerException("Some other shit !!");
    }

    @GetMapping("/excheck")
    public String getCheckedException() throws Exception {
        throw new Exception("This is Local Checked Exception.");
    }

    @PostMapping(value = "/car",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Car createCar(@Valid @RequestBody Car car) {
        return car;
    }

}