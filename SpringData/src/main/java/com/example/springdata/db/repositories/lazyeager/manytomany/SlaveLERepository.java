package com.example.springdata.db.repositories.lazyeager.manytomany;

import com.example.springdata.db.entities.lazyeager.manytomany.SlaveLE;
import com.example.springdata.db.entities.lazyeager.manytomany.SlaveLL;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SlaveLERepository extends JpaRepository<SlaveLE, Long> {
}
