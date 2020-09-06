package com.example.springdata.repositories.osiv;

import com.example.springdata.entities.osiv.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
}
