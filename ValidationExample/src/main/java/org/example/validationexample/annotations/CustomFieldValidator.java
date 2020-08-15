package org.example.validationexample.annotations;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Custom field validation
 */
public class CustomFieldValidator implements ConstraintValidator<CustomFieldValidation, String> {
    @Override
    public boolean isValid(String object, ConstraintValidatorContext constraintValidatorContext) {
        return object.equals("Vasil is the best");
    }
}
