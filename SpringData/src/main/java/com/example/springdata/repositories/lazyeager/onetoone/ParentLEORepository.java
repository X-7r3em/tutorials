package com.example.springdata.repositories.lazyeager.onetoone;

import com.example.springdata.lazyeager.onetoone.ParentLEO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParentLEORepository extends JpaRepository<ParentLEO, Long> {
}
