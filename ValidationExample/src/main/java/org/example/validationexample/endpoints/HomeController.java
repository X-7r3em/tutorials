package org.example.validationexample.endpoints;

import org.example.validationexample.dto.Bear;
import org.example.validationexample.exceptions.CustomException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class HomeController {


    @PostMapping("/bear")
    public void createBear(@Valid @RequestBody Bear bear) {
    }

    @GetMapping("/ex")
    public void throwException() {
        throw new IllegalArgumentException("Some exception message");
    }

    @GetMapping("/custom")
    public void throwCustomException() {
        throw new CustomException("Some custom exception message");
    }
}
