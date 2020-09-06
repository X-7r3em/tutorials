package com.example.springdata.endpoints;

import com.example.springdata.entities.osiv.Book;
import com.example.springdata.models.BookModel;
import com.example.springdata.repositories.osiv.BookRepository;
import com.example.springdata.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class BookController {
    private final BookRepository bookRepository;
    private final BookService bookService;

    @Autowired
    public BookController(BookRepository bookRepository, BookService bookService) {
        this.bookRepository = bookRepository;
        this.bookService = bookService;
    }

    /**
     * Example for Open Session in View - OSIV. If OSIV is turned on, there will be an open session for
     * Hibernate in the View. This way, the Author will be lazily initialized. If it is off, then it will
     * throw an exception.
     *
     * The lazy initialization will be shown when the Hibernate request is printed after the
     * Ending DB request line.
     */
    @GetMapping("/osiv/1")
    public ModelAndView getBookWithOsiv(ModelAndView modelAndView) {
        System.out.println("--------------------- Starting DB request -------------------");
        Book book = bookRepository.findById(1L).get();
        System.out.println("--------------------- Ending DB request -------------------");
        modelAndView.setViewName("book");
        modelAndView.addObject("book", book);
        return modelAndView;
    }

    /**
     * Here we have the Osiv turned off, however {@link BookService} is
     * {@link org.springframework.transaction.annotation.Transactional}. Even though we do not have session
     * in the view, in the getBook() method, we take the Book and Map it to another entity. We have a
     * session for the whole method there, so there are no exceptions thrown. There are not database
     * requests after Ending DB request
     */
    @GetMapping("/osiv/2")
    public ModelAndView getBookWithoutOsiv(ModelAndView modelAndView) {
        System.out.println("--------------------- Starting DB request -------------------");
        BookModel bookModel = bookService.getBook();
        System.out.println("--------------------- Ending DB request -------------------");
        modelAndView.setViewName("bookModel");
        modelAndView.addObject("bookModel", bookModel);
        return modelAndView;
    }


}
