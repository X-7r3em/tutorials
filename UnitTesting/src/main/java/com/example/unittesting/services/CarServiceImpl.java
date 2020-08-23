package com.example.unittesting.services;

import com.example.unittesting.dtos.Car;
import com.example.unittesting.repos.CarRepository;
import org.springframework.stereotype.Service;

@Service
public class CarServiceImpl implements CarService {
    private CarRepository carRepository;

    public CarServiceImpl(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @Override
    public Car save(Car car) {
        return carRepository.save(car);
    }
}
