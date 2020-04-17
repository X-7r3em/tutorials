package com.example.springdata.repositories.lazyeager.manytomany;

import com.example.springdata.lazyeager.manytomany.SlaveLE;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SlaveLERepository extends JpaRepository<SlaveLE, Long> {
}
