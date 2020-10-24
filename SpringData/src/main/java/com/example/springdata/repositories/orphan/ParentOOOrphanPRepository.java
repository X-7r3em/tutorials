package com.example.springdata.repositories.orphan;

import com.example.springdata.entities.orphan.ParentOOOrphanP;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParentOOOrphanPRepository extends JpaRepository<ParentOOOrphanP, Long> {
}
