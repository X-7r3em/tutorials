package com.example.springdata.services;

import com.example.springdata.entities.crud.Book;
import com.example.springdata.models.BookModel;
import com.example.springdata.repositories.crud.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BookServiceImpl implements BookService {
    private BookRepository bookRepository;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    @Transactional
    public BookModel getBook() {
        Book book = bookRepository.findById(1L).get();
        return new BookModel(book.getName(), book.getAuthor().getName());
    }

}
