package com.example.exceptionhandling.endpoints;

import com.example.exceptionhandling.dtos.Car;
import com.example.exceptionhandling.services.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class CarController {
    private final CarService carService;

    @Autowired
    public CarController(CarService carService) {
        this.carService = carService;
    }

    // For throwing MethodArgumentNotValidException
    @PostMapping(value = "/car",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Car createCar(@Valid @RequestBody Car car) {
        return car;
    }

    // For throwing ConstraintDeclarationException
    @PostMapping(value = "/carservice",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Car createCarService(@RequestBody Car car) {
        return carService.createService(car);
    }

    // Request to a proxy
    @PostMapping(value = "/carrequest",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Car proxyRequestCar(@RequestBody Car car) {
        return carService.createProxy(car);
    }

    // Stubbing a proxy for RestTemplate
    @PostMapping(value = "/carproxy",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Car proxyReturnCar(@Valid @RequestBody Car car) {
        return car;
    }
}
