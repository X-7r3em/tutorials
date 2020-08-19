package com.example.validation;

import com.example.validation.dto.nesting.Inner;
import com.example.validation.dto.nesting.OuterNoValidation;
import com.example.validation.dto.nesting.OuterWithValidation;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
public class ValidationOnNestedObjectsTest {

    @Autowired
    private Validator validator;

    @Test
    public void validate_givenOuterNoValidation_willNotValidateNestedObjects() {
        Inner inner = new Inner();
        OuterNoValidation outerNoValidation = new OuterNoValidation("Vasko", inner);

        Set<ConstraintViolation<OuterNoValidation>> validations = validator.validate(outerNoValidation);

        assertEquals(0, validations.size());
    }

    @Test
    public void validate_givenOuterWithValidation_willValidateNestedObjects() {
        Inner invalidInner = new Inner();
        OuterWithValidation outerWithValidation = new OuterWithValidation("Vasko", invalidInner);

        Set<ConstraintViolation<OuterWithValidation>> violations = validator.validate(outerWithValidation);

            List<String> violationMessages = violations.stream()
                    .map(ConstraintViolation::getMessage)
                    .sorted(Comparator.comparing(String::length))
                    .collect(Collectors.toList());

        assertEquals(1, violations.size());
        assertEquals("Inner name can not be null", violationMessages.get(0));
    }
}
