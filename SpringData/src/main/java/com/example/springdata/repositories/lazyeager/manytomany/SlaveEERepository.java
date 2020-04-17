package com.example.springdata.repositories.lazyeager.manytomany;

import com.example.springdata.lazyeager.manytomany.SlaveEE;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SlaveEERepository extends JpaRepository<SlaveEE, Long> {
}
