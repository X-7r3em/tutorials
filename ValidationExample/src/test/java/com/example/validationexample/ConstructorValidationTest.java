package com.example.validationexample;

import com.example.validationexample.dto.ConstructorValidationObject;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.validation.executable.ExecutableValidator;
import java.lang.reflect.Constructor;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ConstructorValidationTest {

    @Autowired
    private Validator validator;

    @Test
    public void validator_givenConstructorParameterConstraints_willNotValidate() {
        ConstructorValidationObject mock = new ConstructorValidationObject(null, 0);

        Set<ConstraintViolation<ConstructorValidationObject>> violations = validator.validate(mock);

        assertEquals(0, violations.size());
    }

    @Test
    public void validator_givenExecutableValidatorAndConstructorParameterConstraints_willValidate() throws NoSuchMethodException {
        ExecutableValidator executableValidator = validator.forExecutables();
        ConstructorValidationObject mock = new ConstructorValidationObject(null, 0);
        Object[] parameters = new Object[] {null, 0};
        Constructor<? extends ConstructorValidationObject> constructor =
                mock.getClass().getConstructor(String.class, int.class);
        Set<ConstraintViolation<ConstructorValidationObject>> violations =
                executableValidator.validateConstructorParameters(constructor, parameters);

        List<String> violationMessages = violations.stream()
                .map(ConstraintViolation::getMessage)
                .sorted(Comparator.comparing(String::length))
                .collect(Collectors.toList());

        assertEquals(2, violations.size());

        assertEquals("must not be null", violationMessages.get(0));
        assertEquals("must be greater than or equal to 10", violationMessages.get(1));
    }
}