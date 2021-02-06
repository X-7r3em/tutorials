package com.example.objectmapper.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

public class Car {
    private int age;
    private String name;

    @JsonDeserialize(using = CarDeserializer.class)
    private String make;

    public Car() {
    }

    public Car(int age, String name, String make) {
        this.age = age;
        this.name = name;
        this.make = make;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    @Override
    public String toString() {
        return "Car{" +
                "age=" + age +
                ", name='" + name + '\'' +
                ", make='" + make + '\'' +
                '}';
    }
}
