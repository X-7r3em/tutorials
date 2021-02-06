package com.example.springdata.repositories.lazyeager.manytomany;

import com.example.springdata.lazyeager.manytomany.SlaveEL;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SlaveELRepository extends JpaRepository<SlaveEL, Long> {
}
