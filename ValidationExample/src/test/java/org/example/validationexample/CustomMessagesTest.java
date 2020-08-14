package org.example.validationexample;

import org.example.validationexample.dto.LoginForm;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Formatter;
import java.util.Set;

@SpringBootTest
public class CustomMessagesTest {

    @Autowired
    private LocalValidatorFactoryBean localValidatorFactoryBean;

    @Test
    public void validate_givenInvalidLoginFormWithCustomMessage_willReturnCustomMessage() {
        LoginForm invalidLoginForm = new LoginForm("", "password");
        Validator val = localValidatorFactoryBean;
        Validator validator = localValidatorFactoryBean.getValidator();
        Set<ConstraintViolation<LoginForm>> validations = val.validate(invalidLoginForm);

        validations.forEach(v -> System.out.println(v.getMessage()));
    }
}
