package com.example.exceptionhandling.dtos;

import javax.validation.constraints.NotNull;

public class Car {
    @NotNull(message = "Make must not be null")
    private String make;
    @NotNull(message = "Model must not be null")
    private String model;

    public Car() {
    }

    public Car(@NotNull(message = "Make must not be null") String make, @NotNull(message = "Model must not be null") String model) {
        this.make = make;
        this.model = model;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    @Override
    public String toString() {
        return "Car{" +
                "make='" + make + '\'' +
                ", model='" + model + '\'' +
                '}';
    }
}
