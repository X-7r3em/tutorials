package org.example.validationexample;

import org.example.validationexample.annotations.CustomMethodValidation;
import org.example.validationexample.dto.CustomFieldValidationObject;
import org.example.validationexample.endpoints.HomeController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.validation.executable.ExecutableValidator;
import java.lang.reflect.Method;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class CustomValidationTest {

    @Autowired
    private Validator validator;

    @Test
    public void customMethodAnnotation_givenInvalidName_willReturnViolation() throws NoSuchMethodException {
        HomeController homeController = new HomeController();
        Method method = homeController.getClass().getMethod("customMethodAnnotation", String.class, String.class);
        Object[] values = new Object[]{"Vasil1", "Egov1"};

        ExecutableValidator executableValidator = validator.forExecutables();
        Set<ConstraintViolation<HomeController>> validations =
                executableValidator.validateParameters(homeController, method, values);

        String message = validations.stream().map(ConstraintViolation::getMessage)
                .findFirst().orElse(null);

        assertEquals(1, validations.size());
        assertEquals("The name is not the one of the admin", message);
    }

    @Test
    public void customMethodAnnotation_givenValidName_willReturnNoViolation() throws NoSuchMethodException {
        HomeController homeController = new HomeController();
        Method method = homeController.getClass().getMethod("customMethodAnnotation", String.class, String.class);
        Object[] values = new Object[]{"Vasil", "Egov"};

        ExecutableValidator executableValidator = validator.forExecutables();
        Set<ConstraintViolation<HomeController>> validations =
                executableValidator.validateParameters(homeController, method, values);

        assertEquals(0, validations.size());
    }

    @Test
    public void customFieldValidation_givenInvalidName_willReturnViolation() {
        CustomFieldValidationObject mock = new CustomFieldValidationObject("Pesho");
        Set<ConstraintViolation<CustomFieldValidationObject>> validations = validator.validate(mock);
        String message = validations.stream().map(ConstraintViolation::getMessage)
                .findFirst().orElse(null);

        assertEquals(1, validations.size());
        assertEquals("The name is not the proper field", message);
    }

    @Test
    public void customFieldValidation_givenValidName_willReturnNoViolations() {
        CustomFieldValidationObject mock = new CustomFieldValidationObject("Vasil is the best");
        Set<ConstraintViolation<CustomFieldValidationObject>> validate = validator.validate(mock);
        assertEquals(0, validate.size());
    }
}
