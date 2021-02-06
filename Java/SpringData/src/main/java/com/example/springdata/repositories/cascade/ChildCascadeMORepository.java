package com.example.springdata.repositories.cascade;

import com.example.springdata.entities.cascade.ChildMOCascade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChildCascadeMORepository extends JpaRepository<ChildMOCascade, Long> {
}
