package org.example.validationexample;

import org.example.validationexample.dto.Car;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class ValidationTest {

    @Autowired
    private Validator validator;

    @Test
    public void validate_givenCar_willValidate() {
        Car car = new Car(null);
        Set<ConstraintViolation<Car>> validation = validator.validate(car);

        List<String> messages = validation.stream().map(ConstraintViolation::getMessage).collect(Collectors.toList());

        assertEquals(1, validation.size());
        assertEquals("must not be null", messages.get(0));
    }
}
