package com.example.validation.services;

import com.example.validation.annotations.CustomMethodValidation;
import com.example.validation.dto.Car;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * Validated enforces the validation annotations on the methods and their arguments to be checked.
 * This also includes the @Valid and @NotNull and other annotation on the argument.
 */
@Service
@Validated
public class DemoService {
    public String methodWithValid(@Valid Car car, @NotNull(message = "Make is null. But why?") String make) {
        return car.toString();
    }

    @CustomMethodValidation
    public String methodWithValidatedAndCustomAnnotation(String firstName, String lastName) {
        return firstName + " " + lastName;
    }
}
