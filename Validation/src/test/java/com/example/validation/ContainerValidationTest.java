package com.example.validation;

import com.example.validation.dto.Car;
import com.example.validation.dto.Person;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.validation.constraints.NotNull;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class ContainerValidationTest {

    @Autowired
    public Validator validator;

    // The container elements will not be validated
    @Test
    public void validate_givenListWithStringAndNull_willNotGiveViolations() {
        List<@NotNull String> strings = new ArrayList<>();
        strings.add(null);
        strings.add("Gosho");
        strings.add("Pesho");

        Set<ConstraintViolation<List<@NotNull String>>> violations = validator.validate(strings);

        assertEquals(0, violations.size());
    }

    // The object will be validated and if his property is a container, it will be validated.
    @Test
    public void validate_givenPersonContainingNullAddresses_willGiveViolation() {
        Person person = new Person();
        List<String> addresses = Arrays.asList(null, "This is the address");
        person.setAddresses(addresses);

        Set<ConstraintViolation<Person>> violations = validator.validate(person);

        assertEquals(1, violations.size());
        assertEquals("Address must not be null", violations.iterator().next().getMessage());
    }

    @Test
    public void validate_givenPersonContainingAddresses_willGiveNoViolation() {
        Person person = new Person();
        List<String> addresses = Arrays.asList("Some other address", "This is the address");
        person.setAddresses(addresses);

        Set<ConstraintViolation<Person>> violations = validator.validate(person);

        assertEquals(0, violations.size());
    }
}
