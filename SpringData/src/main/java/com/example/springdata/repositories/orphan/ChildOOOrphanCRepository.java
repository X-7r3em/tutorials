package com.example.springdata.repositories.orphan;

import com.example.springdata.entities.orphan.ChildOOOrphanC;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChildOOOrphanCRepository extends JpaRepository<ChildOOOrphanC, Long> {
}