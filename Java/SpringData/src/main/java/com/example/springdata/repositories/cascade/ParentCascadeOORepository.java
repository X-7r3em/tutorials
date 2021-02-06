package com.example.springdata.repositories.cascade;

import com.example.springdata.entities.cascade.ParentOOCascade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParentCascadeOORepository extends JpaRepository<ParentOOCascade, Long> {
}
