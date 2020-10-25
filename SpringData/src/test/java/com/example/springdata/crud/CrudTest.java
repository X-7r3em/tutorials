package com.example.springdata.crud;

import com.example.springdata.entities.crud.Author;
import com.example.springdata.entities.crud.Book;
import com.example.springdata.repositories.crud.AuthorRepository;
import com.example.springdata.repositories.crud.BookRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
public class CrudTest {
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    /**
     * Example for saving from the parent relation (owned relation).
     * We need {@link javax.persistence.CascadeType} for this to be saved.
     * Again, {@link Transactional} is needed for the session.
     * <p>
     * Note: {@link Commit} is used so that after the test is done, the actual database
     * transaction is committed, instead of ignored.
     */
    @Transactional
    @Test
    @Commit
    public void savingFromParentRelation() {
        Author author = authorRepository.findById(1L).get();
        Book book = new Book();
        book.setName("From Parent book");

        /*
         * We need to set the author to the book, so that the book knows the author_id.
         * If we omit this step, the book will be saved without the author_id.
         */
        book.setAuthor(author);

        /*
         * We add the book to the books. This way, hibernate will iterate over all the books
         * and save them to the db as they are.
         */
        author.getBooks().add(book);
    }

    /**
     * Example for saving from the child relation (owner relation).
     */
    @Test
    @Commit
    public void savingFromChildRelation() {
        Author author = authorRepository.findById(1L).get();
        Book book = new Book();
        book.setName("From Child book");
        book.setAuthor(author);
        bookRepository.saveAndFlush(book);
    }


    /**
     * The Persistence context will update the name of the book and commit it.
     */
    @Transactional
    @Test
    @Commit
    public void updatingFromParentRelation() {
        Author author = authorRepository.findById(1L).get();
        author.getBooks().iterator().next().setName("This books name has been updated");
    }

    /**
     * The Persistence context will update the name of the book and commit it.
     */
    @Transactional
    @Test
    @Commit
    public void updatingFromChildRelation() {
        Book book = bookRepository.findById(1L).get();
        book.setName(book.getName() + " was updated!");
    }

    /**
     * Creating a new entity with an ID and trying to persist it will not happen as
     * the new entity is detached (has an ID but is not in the JPA Context).
     */
    @Transactional
    @Test
    @Commit
    public void addingNewChildWithIdToParent_willNotPersist() {
        Author author = authorRepository.findById(1L).get();
        Book book = new Book();
        book.setAuthor(author);
        book.setName("Newly added book of author");
        // Uncomment this to receive the desired result. Can not catch the exception due to @Transactional.
        //book.setId(10);
        author.getBooks().add(book);
    }

}
