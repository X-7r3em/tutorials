package com.example.springdata.repositories.cascade;

import com.example.springdata.entities.cascade.ParentMOCascade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParentCascadeMORepository extends JpaRepository<ParentMOCascade, Long> {
}
