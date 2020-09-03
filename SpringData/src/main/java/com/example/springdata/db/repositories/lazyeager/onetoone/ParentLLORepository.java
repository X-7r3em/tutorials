package com.example.springdata.db.repositories.lazyeager.onetoone;

import com.example.springdata.db.entities.lazyeager.onetoone.ParentLLO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParentLLORepository extends JpaRepository<ParentLLO, Long> {
}
