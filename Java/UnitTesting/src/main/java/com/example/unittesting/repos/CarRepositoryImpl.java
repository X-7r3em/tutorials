package com.example.unittesting.repos;

import com.example.unittesting.dtos.Car;
import org.springframework.stereotype.Repository;

@Repository
public class CarRepositoryImpl implements CarRepository {
    @Override
    public Car save(Car car) {
        return car;
    }
}
