package com.example.swaggerexample.models;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

// Swagger documentation of the Model itself
@ApiModel(description = "This is the container of a Contact")
public class Contact {

    // Swagger documentation for the separate property
    @ApiModelProperty(value = "This is the ID property of the object")
    private String id;
    private String name;
    private String phone;

    public Contact() {
    }

    public Contact(String id, String name, String phone) {
        this.id = id;
        this.name = name;
        this.phone = phone;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
