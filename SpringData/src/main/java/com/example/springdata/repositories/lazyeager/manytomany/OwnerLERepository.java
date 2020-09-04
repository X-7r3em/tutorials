package com.example.springdata.repositories.lazyeager.manytomany;

import com.example.springdata.entities.lazyeager.manytomany.OwnerLE;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OwnerLERepository extends JpaRepository<OwnerLE, Long> {
}
