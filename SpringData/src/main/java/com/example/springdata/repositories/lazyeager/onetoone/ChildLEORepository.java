package com.example.springdata.repositories.lazyeager.onetoone;

import com.example.springdata.lazyeager.onetoone.ChildLEO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChildLEORepository extends JpaRepository<ChildLEO, Long> {
}
