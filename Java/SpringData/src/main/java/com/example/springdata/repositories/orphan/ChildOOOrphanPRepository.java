package com.example.springdata.repositories.orphan;

import com.example.springdata.entities.orphan.ChildOOOrphanP;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChildOOOrphanPRepository extends JpaRepository<ChildOOOrphanP, Long> {
}