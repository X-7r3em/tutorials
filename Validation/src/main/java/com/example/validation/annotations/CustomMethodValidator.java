package com.example.validation.annotations;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.constraintvalidation.SupportedValidationTarget;
import javax.validation.constraintvalidation.ValidationTarget;

/**
 * Validates the parameters of a method.
 */
@SupportedValidationTarget(ValidationTarget.PARAMETERS)
public class CustomMethodValidator
        implements ConstraintValidator<CustomMethodValidation, Object[]> {

    @Override
    public boolean isValid(
            Object[] value,
            ConstraintValidatorContext context) {

        if (value[0] == null || value[1] == null) {
            return true;
        }

        if (!(value[0] instanceof String)
                || !(value[1] instanceof String)) {
            throw new IllegalArgumentException(
                    "Illegal method signature, expected two parameters of type String.");
        }

        return value[0].equals("Vasil") && value[1].equals("Egov");
    }
}
