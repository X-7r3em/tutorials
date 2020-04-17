package com.example.exceptionhandling.dtos;

import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Objects;

public class ProxyResponseContainer extends ProxyResponse {
    private String make;
    private String model;

    public ProxyResponseContainer() {
    }

    public ProxyResponseContainer(HttpStatus status, String message, List<String> errors, String make, String model) {
        super(status, message, errors);
        this.make = make;
        this.model = model;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        ProxyResponseContainer that = (ProxyResponseContainer) o;
        return Objects.equals(make, that.make) &&
                Objects.equals(model, that.model);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), make, model);
    }

    @Override
    public String toString() {
        return "ProxyResponseContainer{" +
                "make='" + make + '\'' +
                ", model='" + model + '\'' +
                "} " + super.toString();
    }
}
