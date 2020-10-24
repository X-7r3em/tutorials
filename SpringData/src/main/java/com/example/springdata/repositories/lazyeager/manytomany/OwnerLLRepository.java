package com.example.springdata.repositories.lazyeager.manytomany;

import com.example.springdata.lazyeager.manytomany.OwnerLL;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OwnerLLRepository extends JpaRepository<OwnerLL, Long> {
}
