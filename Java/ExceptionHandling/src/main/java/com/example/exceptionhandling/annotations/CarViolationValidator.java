package com.example.exceptionhandling.annotations;

import com.example.exceptionhandling.dtos.Car;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CarViolationValidator implements ConstraintValidator<CarViolation, Car> {
    @Override
    public boolean isValid(Car car, ConstraintValidatorContext constraintValidatorContext) {
        return car.getModel() != null && car.getMake() != null;
    }
}
