package com.example.springdata.repositories.lazyeager.manytoone;

import com.example.springdata.lazyeager.manytoone.ParentEE;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParentEERepository extends JpaRepository<ParentEE, Long> {
}
