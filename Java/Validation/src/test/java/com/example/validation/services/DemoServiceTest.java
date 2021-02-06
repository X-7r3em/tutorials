package com.example.validation.services;

import com.example.validation.dto.Car;
import com.example.validation.dto.GroupContainer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.validation.ConstraintViolationException;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class DemoServiceTest {

    @Autowired
    private DemoService demoService;

    /**
     * The @Validated and validation on method arguments will be called when in a Spring Context Application
     * even if not called from a Controller
     */
    @Test
    public void methodWithValid_givenMake_willReturnString() {
        Car car = new Car("Ferrari");

        String actual = demoService.methodWithValid(car, car.getMake());

        assertEquals("Car{make='Ferrari'}", actual);
    }

    @Test
    public void methodWithValid_givenInvalidMake_willThrowException() {
        Car car = new Car();

        ConstraintViolationException actual =
                assertThrows(ConstraintViolationException.class, () -> demoService.methodWithValid(car, car.getMake()));

        List<String> violations = Arrays.stream(actual.getMessage().split(", "))
                .sorted(Comparator.comparing(String::length))
                .collect(Collectors.toList());

        assertEquals(2, actual.getConstraintViolations().size());
        assertEquals("methodWithValid.car.make: must not be null", violations.get(0));
        assertEquals("methodWithValid.make: Make is null. But why?", violations.get(1));
    }

    @Test
    public void methodWithValidatedAndCustomAnnotation_givenValidFirstAndLastName_willReturnAdminName() {
        String firstName = "Vasil";
        String lastName = "Egov";

        String adminName = demoService.methodWithValidatedAndCustomAnnotation(firstName, lastName);

        assertEquals(firstName + " " + lastName, adminName);
    }

    @Test
    public void methodWithValidatedAndCustomAnnotation_givenInvalidFirstAndLastName_willThrowException() {
        String firstName = "wrong";
        String lastName = "invalid";

        ConstraintViolationException exception = assertThrows(ConstraintViolationException.class,
                () -> demoService.methodWithValidatedAndCustomAnnotation(firstName, lastName));

        assertEquals(1, exception.getConstraintViolations().size());
        assertEquals("methodWithValidatedAndCustomAnnotation.<cross-parameter>: The name is not the one of the admin", exception.getMessage());
    }

    /**
     * The next 4 tests show the Group validation functionality
     */
    @Test
    public void create_givenValidGroupContainer_willReturnGroupContainer() {
        GroupContainer groupContainer = new GroupContainer();

        GroupContainer actual = demoService.create(groupContainer);

        assertEquals("Container", actual.getName());
    }

    @Test
    public void create_givenInvalidValidGroupContainer_willThrowException() {
        GroupContainer groupContainer = new GroupContainer("I am not empty");

        ConstraintViolationException exception = assertThrows(ConstraintViolationException.class,
                () -> demoService.create(groupContainer));

        assertEquals(1, exception.getConstraintViolations().size());
        assertEquals("create.groupContainer.name: I must be empty on create", exception.getMessage());
    }

    @Test
    public void update_givenValidGroupContainer_willReturnGroupContainer() {
        GroupContainer groupContainer = new GroupContainer("I have the update");

        GroupContainer actual = demoService.update(groupContainer);

        assertEquals(groupContainer.getName(), actual.getName());
    }

    @Test
    public void update_givenInvalidValidGroupContainer_willThrowException() {
        GroupContainer groupContainer = new GroupContainer();

        ConstraintViolationException exception = assertThrows(ConstraintViolationException.class,
                () -> demoService.update(groupContainer));

        assertEquals(1, exception.getConstraintViolations().size());
        assertEquals("update.groupContainer.name: I must not be empty on update", exception.getMessage());
    }
}
