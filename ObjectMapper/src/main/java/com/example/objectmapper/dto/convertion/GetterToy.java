package com.example.objectmapper.dto.convertion;

import com.fasterxml.jackson.annotation.JsonGetter;

public class GetterToy {
    private String name;

    public GetterToy() {
    }

    public GetterToy(String name) {
        this.name = name;
    }

    @JsonGetter("GetterName")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "GetterToy{" +
                "name='" + name + '\'' +
                '}';
    }
}
