package com.example.exceptionhandling.dtos;

import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Objects;

public class ProxyResponseContainer {
    private String make;
    private String model;

    // ApiError fields. Can be done with inheritance and a similar ancestor.
    private HttpStatus status;
    private String message;
    private List<String> errors;

    public ProxyResponseContainer() {
    }

    public ProxyResponseContainer(String make, String model, HttpStatus status, String message, List<String> errors) {
        this.make = make;
        this.model = model;
        this.status = status;
        this.message = message;
        this.errors = errors;
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

    public HttpStatus getStatus() {
        return status;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProxyResponseContainer that = (ProxyResponseContainer) o;
        return Objects.equals(make, that.make) &&
                Objects.equals(model, that.model) &&
                status == that.status &&
                Objects.equals(message, that.message) &&
                Objects.equals(errors, that.errors);
    }

    @Override
    public int hashCode() {
        return Objects.hash(make, model, status, message, errors);
    }

    @Override
    public String toString() {
        return "ProxyResponseContainer{" +
                "make='" + make + '\'' +
                ", model='" + model + '\'' +
                ", status=" + status +
                ", message='" + message + '\'' +
                ", errors=" + errors +
                '}';
    }
}
