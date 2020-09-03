package com.example.springdata.db.repositories.lazyeager;

import com.example.springdata.db.entities.lazyeager.ParentEL;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParentELRepository extends JpaRepository<ParentEL, Long> {
}
