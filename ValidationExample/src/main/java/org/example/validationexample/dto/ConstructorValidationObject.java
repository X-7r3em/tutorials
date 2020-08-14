package org.example.validationexample.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class ConstructorValidationObject {
    private String name;
    private int age;

    public ConstructorValidationObject(@NotNull String name, @Min(10) int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
