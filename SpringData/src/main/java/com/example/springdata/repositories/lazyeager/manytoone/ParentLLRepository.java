package com.example.springdata.repositories.lazyeager.manytoone;

import com.example.springdata.lazyeager.manytoone.ParentLL;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParentLLRepository extends JpaRepository<ParentLL, Long> {
}
