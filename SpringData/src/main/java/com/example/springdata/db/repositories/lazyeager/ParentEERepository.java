package com.example.springdata.db.repositories.lazyeager;

import com.example.springdata.db.entities.lazyeager.ParentEE;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParentEERepository extends JpaRepository<ParentEE, Long> {
}
