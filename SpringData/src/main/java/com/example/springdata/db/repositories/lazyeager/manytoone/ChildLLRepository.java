package com.example.springdata.db.repositories.lazyeager.manytoone;

import com.example.springdata.db.entities.lazyeager.manytoone.ChildLL;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChildLLRepository extends JpaRepository<ChildLL, Long> {
}
