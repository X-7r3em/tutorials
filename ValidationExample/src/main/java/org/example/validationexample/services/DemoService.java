package org.example.validationexample.services;

import org.example.validationexample.annotations.CustomMethodValidation;
import org.example.validationexample.annotations.CustomMethodValidator;
import org.example.validationexample.dto.Car;
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
    // ToDo More examples with this. The Validated makes it so that the argumets are validated. Can I validate rest template?!?!??
    public String methodWithValid(@Valid Car car, @NotNull(message = "Make is null be") String make) {

        return car.toString();
    }

    @CustomMethodValidation
    public String methodWithValidatedAndCustomAnnotation(String firstName, String lastName) {
        return firstName + " " + lastName;
    }
}
