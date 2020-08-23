package com.example.exceptionhandling.annotations;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = CarViolationValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface CarViolation {
    String message() default "Invalid car";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
