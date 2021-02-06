package com.example;

import org.modelmapper.ModelMapper;

public class Main {
    public static void main(String[] args) {
        ModelMapper modelMapper = new ModelMapper();
        Person person = new Person("Vasil");
        PersonDto personDto = modelMapper.map(person, PersonDto.class);
        System.out.println("Hello, " + personDto.getName() + "!".repeat(50));
    }
}
