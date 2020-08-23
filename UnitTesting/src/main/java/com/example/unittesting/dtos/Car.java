package com.example.unittesting.dtos;

public class Car {
    private String make;
    private String brand;

    public Car() {
    }

    public Car(String make, String brand) {
        this.make = make;
        this.brand = brand;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

}
