package com.example.objectmapper.dto.convertion;

import com.fasterxml.jackson.annotation.JsonSetter;

public class SetterToy {
    private String name;

    public SetterToy() {
    }

    public SetterToy(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @JsonSetter("SetterName")
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "SetterToy{" +
                "name='" + name + '\'' +
                '}';
    }
}
