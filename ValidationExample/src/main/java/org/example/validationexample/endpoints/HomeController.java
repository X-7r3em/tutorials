package org.example.validationexample.endpoints;

import org.example.validationexample.annotations.CustomMethodValidation;
import org.example.validationexample.dto.Bear;
import org.example.validationexample.dto.Car;
import org.example.validationexample.exceptions.CustomException;
import org.example.validationexample.services.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @Validated causes Spring to automatically validate the methods, which are annotated for validation
 * The exception throw is javax.validation.ConstraintViolationException
 */
@Validated
@RestController
public class HomeController {

    @Autowired
    private DemoService demoService;

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

    @GetMapping("/admin")
    @CustomMethodValidation
    public String customMethodAnnotation(@RequestParam String firstName, @RequestParam String lastName) {
        return firstName + " " + lastName;
    }

    // ToDo - create controller tests
    @GetMapping("/validated")
    public String methodCustomException(@RequestParam String firstName, @RequestParam String lastName) {
        return demoService.methodWithValidatedAndCustomAnnotation(firstName, lastName);
    }

    @GetMapping("/valid")
    public String methodWithValid() {
        return demoService.methodWithValid(new Car("Gosho"), null);
    }
}


