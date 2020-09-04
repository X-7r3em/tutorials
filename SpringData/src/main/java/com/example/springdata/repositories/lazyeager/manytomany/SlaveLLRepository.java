package com.example.springdata.repositories.lazyeager.manytomany;

import com.example.springdata.entities.lazyeager.manytomany.SlaveLL;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SlaveLLRepository extends JpaRepository<SlaveLL, Long> {
}
