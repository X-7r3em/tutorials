package com.example.springdata.repositories.lazyeager.manytoone;

import com.example.springdata.entities.lazyeager.manytoone.ParentEL;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParentELRepository extends JpaRepository<ParentEL, Long> {
}
