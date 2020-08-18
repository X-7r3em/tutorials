package com.example.validationexample.annotations;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Constraint(validatedBy = CustomFieldValidator.class)
@Target(FIELD)
@Retention(RUNTIME)
@Documented
public @interface CustomFieldValidation {

        String message() default
                "The name is not the proper field";

        Class<?>[] groups() default {};

        Class<? extends Payload>[] payload() default {};
}
