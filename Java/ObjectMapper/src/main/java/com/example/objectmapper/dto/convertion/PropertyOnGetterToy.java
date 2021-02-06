package com.example.objectmapper.dto.convertion;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PropertyOnGetterToy {
    private String name;

    public PropertyOnGetterToy() {
    }

    public PropertyOnGetterToy(String name) {
        this.name = name;
    }

    @JsonProperty("PropertyName")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "PropertyOnGetterToy{" +
                "name='" + name + '\'' +
                '}';
    }
}
