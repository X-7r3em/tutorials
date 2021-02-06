package com.example.h2db.endpoints;

import com.example.h2db.db.CarRepository;
import com.example.h2db.dtos.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CarController {
    private final CarRepository carRepository;

    @Autowired
    public CarController(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @GetMapping("/cars")
    public List<Car> get() {
        return carRepository.findAll();
    }

    @PostMapping("/cars")
    public Car post(@RequestBody Car car) {
        return carRepository.saveAndFlush(car);
    }
}
