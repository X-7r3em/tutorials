package com.example.springdata.db.repositories.lazyeager.manytoone;

import com.example.springdata.db.entities.lazyeager.manytoone.ParentEE;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParentEERepository extends JpaRepository<ParentEE, Long> {
}
