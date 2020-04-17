package com.example.objectmapper.dto.convertion;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonSetter;

public class GetterSetterToy {
    private String name;

    public GetterSetterToy() {
    }

    public GetterSetterToy(String name) {
        this.name = name;
    }

    @JsonGetter("GetterName")
    public String getName() {
        return name;
    }

    @JsonSetter("SetterName")
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "GetterSetterToy{" +
                "name='" + name + '\'' +
                '}';
    }
}
