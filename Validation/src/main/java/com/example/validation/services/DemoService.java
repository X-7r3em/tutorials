package com.example.validation.services;

import com.example.validation.annotations.CustomMethodValidation;
import com.example.validation.annotations.OnCreate;
import com.example.validation.annotations.OnUpdate;
import com.example.validation.dto.Car;
import com.example.validation.dto.GroupContainer;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * Validated enforces the validation annotations on the methods and their arguments to be checked.
 * This also includes the @Valid and @NotNull and other annotation on the argument.
 */
@Service
@Validated
public class DemoService {
    public String methodWithValid(@Valid Car car, @NotNull(message = "Make is null. But why?") String make) {
        return car.toString();
    }

    @CustomMethodValidation
    public String methodWithValidatedAndCustomAnnotation(String firstName, String lastName) {
        return firstName + " " + lastName;
    }

    /**
     * We are using a group {@link OnCreate} or {@link OnUpdate} to select which validation we want
     * on the method arguments.
     *
     * The {@link Validated} shows which group we need, but also we need {@link Validated} or another
     * argument annotation to initiate the validation. If we omit {@link Valid} or another argument annotation,
     * then no validation will take place!
     *
     * @param groupContainer - mock object
     * @return - mock return
     */
    @Validated(OnCreate.class)
    public GroupContainer create(@Valid GroupContainer groupContainer) {
        groupContainer.setName("Container");
        return groupContainer;
    }

    @Validated(OnUpdate.class)
    public GroupContainer update(@Valid GroupContainer groupContainer) {
        return groupContainer;
    }
}
