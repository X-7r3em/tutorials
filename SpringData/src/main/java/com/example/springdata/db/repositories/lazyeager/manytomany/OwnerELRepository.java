package com.example.springdata.db.repositories.lazyeager.manytomany;

import com.example.springdata.db.entities.lazyeager.manytomany.OwnerEL;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OwnerELRepository extends JpaRepository<OwnerEL, Long> {
}
