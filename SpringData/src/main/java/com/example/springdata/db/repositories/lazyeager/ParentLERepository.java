package com.example.springdata.db.repositories.lazyeager;

import com.example.springdata.db.entities.lazyeager.ParentLE;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParentLERepository extends JpaRepository<ParentLE, Long> {
}
