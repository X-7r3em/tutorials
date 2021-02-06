package com.example.springdata.repositories.orphan;

import com.example.springdata.entities.orphan.ChildOMOrphan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChildOMOrphanRepository extends JpaRepository<ChildOMOrphan, Long> {
}