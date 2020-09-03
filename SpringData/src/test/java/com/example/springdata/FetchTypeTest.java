package com.example.springdata;

import com.example.springdata.db.entities.School;
import com.example.springdata.db.entities.Teacher;
import com.example.springdata.db.repositories.PersonRepository;
import com.example.springdata.db.repositories.SchoolRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class FetchTypeTest {

    // Child Entity
    @Autowired
    private PersonRepository personRepository;

    // Parent Entity
    @Autowired
    private SchoolRepository schoolRepository;

    @BeforeEach
    public void setup() {
    }

    //    @ManyToOne(fetch = FetchType.EAGER)
    //    @JoinColumn(name = "school_id_eager_lazy")
    //    private School schoolEagerLazy;
    //
    //    @ManyToOne(fetch = FetchType.LAZY)
    //    @JoinColumn(name = "school_id_lazy_lazy")
    //    private School schoolLazyLazy;
    //
    //    @ManyToOne(fetch = FetchType.EAGER)
    //    @JoinColumn(name = "school_id_eager_eager")
    //    private School schoolEagerEager;
    //
    //    @ManyToOne(fetch = FetchType.LAZY)
    //    @JoinColumn(name = "school_id_lazy_eager")
    //    private School schoolLazyEager;

    /**
     * {@link Transactional} guarantees that I have an open Persistence Context (Hibernate session)
     * and I can fetch lazily the needed objects.
     */
    @Test
    @Transactional
    public void whenReadFromParentRepository_givenEagerParentFetchAndLazyChildFetch_shouldExecuteASingleSqlRequest() {
        printMessage("Parent request");
        School school = schoolRepository.findById(1L).get();

        printMessage("Child Request");
        Set<Teacher> teachersEagerEager = school.getTeachersEagerEager();
    }


    private void printMessage(String message) {
        System.out.printf("-------------------- %s --------------------%n", message);
    }
}
