package com.example.springdata.repositories.lazyeager.onetoone;

import com.example.springdata.entities.lazyeager.onetoone.ChildEEO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChildEEORepository extends JpaRepository<ChildEEO, Long> {
}
