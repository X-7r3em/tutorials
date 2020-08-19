package com.example.validation.dto;

import javax.validation.constraints.NotNull;
import java.util.List;

public class Person {
    private String name;
    private List<@NotNull(message = "Address must not be null") String> addresses;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<String> addresses) {
        this.addresses = addresses;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", addresses=" + addresses +
                '}';
    }
}
