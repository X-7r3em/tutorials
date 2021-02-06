package com.example.springdata.repositories.lazyeager.manytoone;

import com.example.springdata.lazyeager.manytoone.ChildEE;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChildEERepository extends JpaRepository<ChildEE, Long> {
}
