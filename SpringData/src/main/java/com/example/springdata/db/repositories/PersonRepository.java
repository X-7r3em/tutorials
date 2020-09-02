package com.example.springdata.db.repositories;

import com.example.springdata.db.entities.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Teacher, Long> {
}
