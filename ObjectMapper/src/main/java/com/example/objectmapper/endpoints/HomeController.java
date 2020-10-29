package com.example.objectmapper.endpoints;

import com.example.objectmapper.dto.Car;
import com.example.objectmapper.dto.Dto;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.example.objectmapper.dto.Person;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class HomeController {

    @GetMapping("/")
    public void doStuff() throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        HttpHeaders sirmaRequestHeaders = new HttpHeaders();
        sirmaRequestHeaders.setContentType(MediaType.APPLICATION_JSON);
        Car car = new Car(10, "Vasil", "BMW");
        HttpEntity<Car> sirmaRequest = new HttpEntity<>(car, sirmaRequestHeaders);
        System.out.println(objectMapper.writeValueAsString(sirmaRequest));
        Dto carDto = new Dto("some-id", car);
        Person person = new Person(15, "Vasko");
        System.out.println(objectMapper.writeValueAsString(carDto));
        Dto personDto = new Dto("again Id", person);
        System.out.println(objectMapper.writeValueAsString(personDto));
        String carJson = objectMapper.writeValueAsString(car);

        System.out.println(carJson);
        JsonNode carNode = objectMapper.readTree(carJson);
        JsonNode ageNode = carNode.get("age");
        JsonNode nameNode = carNode.get("name");

        System.out.println();
        System.out.println(ageNode.asInt());
        System.out.println(ageNode.asText());
        System.out.println(nameNode.textValue());
        System.out.println(nameNode.asText());
        System.out.println();

        Car newCar = objectMapper.readValue(carJson, Car.class);
        System.out.println(newCar);
    }
}
