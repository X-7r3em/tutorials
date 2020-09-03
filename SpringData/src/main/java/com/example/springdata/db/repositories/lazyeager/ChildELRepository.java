package com.example.springdata.db.repositories.lazyeager;

import com.example.springdata.db.entities.lazyeager.ChildEL;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChildELRepository extends JpaRepository<ChildEL, Long> {
}
