package com.example.springdata.db.repositories.lazyeager;

import com.example.springdata.db.entities.lazyeager.ChildEE;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChildEERepository extends JpaRepository<ChildEE, Long> {
}
