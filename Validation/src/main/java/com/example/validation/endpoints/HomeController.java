package com.example.validation.endpoints;

import com.example.validation.annotations.CustomMethodValidation;
import com.example.validation.dto.Bear;
import com.example.validation.dto.Car;
import com.example.validation.exceptions.CustomException;
import com.example.validation.services.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.awt.*;

/**
 * @Validated causes Spring to automatically validate the methods, which are annotated for validation
 * The exception throw is javax.validation.ConstraintViolationException
 */
@Validated
@RestController
public class HomeController {

    @Autowired
    private DemoService demoService;

    @PostMapping(value = "/bear",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Bear createBear(@Valid @RequestBody Bear bear) {
        return bear;
    }

    @GetMapping("/ex")
    public void throwException() {
        throw new IllegalArgumentException("Some exception message");
    }

    @GetMapping("/custom")
    public void throwCustomException() {
        throw new CustomException("Some custom exception message");
    }

    @GetMapping(value = "/admin",
            produces = MediaType.APPLICATION_JSON_VALUE)
    @CustomMethodValidation
    public String customMethodAnnotation(@RequestParam String firstName, @RequestParam String lastName) {
        return firstName + " " + lastName;
    }

    @GetMapping(value = "/validated",  produces = MediaType.APPLICATION_JSON_VALUE)
    public String methodCustomException(@RequestParam String firstName, @RequestParam String lastName) {
        return demoService.methodWithValidatedAndCustomAnnotation(firstName, lastName);
    }

    @GetMapping("/valid")
    public String methodWithValid(@RequestParam(required = false) String make) {
        return demoService.methodWithValid(new Car(make), make);
    }
}


