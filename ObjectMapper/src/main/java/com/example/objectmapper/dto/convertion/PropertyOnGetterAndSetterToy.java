package com.example.objectmapper.dto.convertion;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PropertyOnGetterAndSetterToy {
    private String name;

    public PropertyOnGetterAndSetterToy() {
    }

    public PropertyOnGetterAndSetterToy(String name) {
        this.name = name;
    }

    @JsonProperty("GetterProperty")
    public String getName() {
        return name;
    }

    @JsonProperty("SetterProperty")
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "PropertyOnGetterAndSetterToy{" +
                "name='" + name + '\'' +
                '}';
    }
}
