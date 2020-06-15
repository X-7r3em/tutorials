package org.example.h2db.db;

import org.example.h2db.dtos.Car;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarRepository extends JpaRepository<Car, Long> {
}
