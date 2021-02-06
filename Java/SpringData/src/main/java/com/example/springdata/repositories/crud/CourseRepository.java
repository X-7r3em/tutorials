package com.example.springdata.repositories.crud;

import com.example.springdata.entities.crud.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {
}
