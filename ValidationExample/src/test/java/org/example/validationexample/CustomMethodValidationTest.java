package org.example.validationexample;

import org.example.validationexample.annotations.CustomMethodValidation;
import org.example.validationexample.dto.CustomFieldValidationObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class CustomMethodValidationTest {

    @Autowired
    private Validator validator;

    @Test
    public void customMethodAnnotation_givenInvalidName_willThrowException() {
        customMethodAnnotation("Vasil", "Egov1");
    }

    @CustomMethodValidation
    public void customMethodAnnotation(String firstName, String lastName) {
    }

    @Test
    public void customFieldValidation_givenInvalidName_willThrowException() {
        CustomFieldValidationObject mock = new CustomFieldValidationObject("Pesho");
        Set<ConstraintViolation<CustomFieldValidationObject>> validations = validator.validate(mock);
        String message = validations.stream().map(ConstraintViolation::getMessage)
                .findFirst().orElse(null);

        assertEquals(1, validations.size());
        assertEquals("The name is not the proper field", message);
    }

    @Test
    public void customFieldValidation_givenValidName_willNotThrowException() {
        CustomFieldValidationObject mock = new CustomFieldValidationObject("Vasil is the best");
        Set<ConstraintViolation<CustomFieldValidationObject>> validate = validator.validate(mock);
        assertEquals(0, validate.size());
    }
}
