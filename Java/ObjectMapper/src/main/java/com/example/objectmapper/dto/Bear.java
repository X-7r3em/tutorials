package com.example.objectmapper.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Bear {
    private String name;
    private Claw claw;

    public Bear() {
    }

    @JsonCreator
    public Bear(@JsonProperty String name, @JsonProperty int size) {
        this.name = name;
        this.claw = new Claw(size);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Claw getClaw() {
        return claw;
    }

    public void setClaw(Claw claw) {
        this.claw = claw;
    }

    @Override
    public String toString() {
        return "Bear{" +
                "name='" + name + '\'' +
                ", claw=" + claw +
                '}';
    }
}
