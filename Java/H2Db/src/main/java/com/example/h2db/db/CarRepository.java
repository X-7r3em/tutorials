package com.example.h2db.db;

import com.example.h2db.dtos.Car;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarRepository extends JpaRepository<Car, Long> {
}
