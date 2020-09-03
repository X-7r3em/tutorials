package com.example.springdata.db.repositories.lazyeager.onetoone;

import com.example.springdata.db.entities.lazyeager.onetoone.ParentELO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParentELORepository extends JpaRepository<ParentELO, Long> {
}
