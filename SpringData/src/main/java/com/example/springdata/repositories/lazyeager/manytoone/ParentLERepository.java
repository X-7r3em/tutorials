package com.example.springdata.repositories.lazyeager.manytoone;

import com.example.springdata.lazyeager.manytoone.ParentLE;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParentLERepository extends JpaRepository<ParentLE, Long> {
}
