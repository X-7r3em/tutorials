package com.example.validation;

import com.example.validation.dto.Bear;
import com.example.validation.dto.Car;
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

    @Test
    public void validate_givenBear_willValidate() {
        Bear bear = new Bear("Bo", 3);
        Set<ConstraintViolation<Bear>> validation = validator.validate(bear);

        List<String> messages = validation.stream()
                .map(ConstraintViolation::getMessage)
                .sorted(Comparator.comparing(String::length))
                .collect(Collectors.toList());

        assertEquals(2, validation.size());
        assertEquals("Age must be at least 10 but was 3", messages.get(0));
        assertEquals("Name must be between 5 and 10, but was 'Bo'", messages.get(1));
    }
}
