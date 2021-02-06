package com.example.springdata.repositories.cascade;

import com.example.springdata.entities.cascade.ChildOOCascade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChildCascadeOORepository extends JpaRepository<ChildOOCascade, Long> {
}
