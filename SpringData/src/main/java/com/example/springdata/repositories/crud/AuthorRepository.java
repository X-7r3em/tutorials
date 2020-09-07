package com.example.springdata.repositories.crud;

import com.example.springdata.entities.crud.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}
