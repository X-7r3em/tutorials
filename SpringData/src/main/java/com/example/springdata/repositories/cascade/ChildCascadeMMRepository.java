package com.example.springdata.repositories.cascade;

import com.example.springdata.entities.cascade.ChildMMCascade;
import com.example.springdata.entities.cascade.ChildMOCascade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChildCascadeMMRepository extends JpaRepository<ChildMMCascade, Long> {
}
