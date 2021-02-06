package com.example.springdata.repositories.lazyeager.onetoone;

import com.example.springdata.lazyeager.onetoone.ChildLLO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChildLLORepository extends JpaRepository<ChildLLO, Long> {
}
