package com.example.validation.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Bear {
    @NotNull(message = "Can no be null")
    @Size(min = 5, max = 10, message = "Name must be between {min} and {max}, but was '${validatedValue}'")
    private String name;

    @Min(value = 10, message = "Age must be at least {value} but was ${formatter.format('%1$d', validatedValue)}")
    private int age;

    public Bear() {
    }

    public Bear(@NotNull(message = "Can no be null") @Size(min = 5, max = 10, message = "Name must be between {min} and {max}, but was '${validatedValue}'") String name, @Min(value = 10, message = "Some message test = ${formatter.format('%1$.2f', validatedValue)}") int age) {
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
