package com.example.springdata;

import com.example.springdata.db.entities.Teacher;
import com.example.springdata.db.entities.School;
import com.example.springdata.db.repositories.PersonRepository;
import com.example.springdata.db.repositories.SchoolRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class FetchTypeTest {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private SchoolRepository schoolRepository;

    @BeforeEach
    public void setup() {
    }

    @Test
    @Transactional
    public void testing() {
        System.out.println("------------- Starting Transaction --------------");


    }

    @Test
    public void testing2() {
        System.out.println(personRepository.findById(1L).get());
    }
}
