package com.example.objectmapper.dto.convertion;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PropertyOnSetterToy {
    private String name;

    public PropertyOnSetterToy() {
    }

    public PropertyOnSetterToy(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @JsonProperty("PropertyName")
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "PropertyOnSetterToy{" +
                "name='" + name + '\'' +
                '}';
    }
}
