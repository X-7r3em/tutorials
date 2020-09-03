package com.example.springdata.db.repositories.lazyeager;

import com.example.springdata.db.entities.lazyeager.ChildLE;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChildLERepository extends JpaRepository<ChildLE, Long> {
}
