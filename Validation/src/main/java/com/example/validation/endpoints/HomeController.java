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

/**
 * {@link Validated} causes Spring to automatically validate the methods, which are annotated for validation
 * The exception throw is javax.validation.ConstraintViolationException
 */

@RestController
public class HomeController {

    @Autowired
    private DemoService demoService;

    /**
     * This will throw a {@link org.springframework.web.bind.MethodArgumentNotValidException} if the
     * {@link Bear} is not a valid object
     * @param bear - sample object
     * @return bear
     */
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

    /**
     * Given invalid first or last name will throw {@link javax.validation.ConstraintViolationException}
     *
     * @param firstName - first name of admin
     * @param lastName - last name of admin
     * @return - the admin name
     */
    @GetMapping(value = "/validated",  produces = MediaType.APPLICATION_JSON_VALUE)
    public String methodCustomException(@RequestParam String firstName, @RequestParam String lastName) {
        return demoService.methodWithValidatedAndCustomAnnotation(firstName, lastName);
    }

    @GetMapping("/valid")
    public String methodWithValid(@RequestParam(required = false) String make) {
        return demoService.methodWithValid(new Car(make), make);
    }
}


