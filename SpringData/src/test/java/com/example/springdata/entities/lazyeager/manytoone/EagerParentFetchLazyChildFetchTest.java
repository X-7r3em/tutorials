package com.example.springdata.entities.lazyeager.manytoone;

import com.example.springdata.AbstractUnitTest;
import com.example.springdata.repositories.lazyeager.manytoone.ChildELRepository;
import com.example.springdata.repositories.lazyeager.manytoone.ParentELRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class EagerParentFetchLazyChildFetchTest extends AbstractUnitTest {

    // Child Entity
    @Autowired
    private ParentELRepository parentRepository;

    // Parent Entity
    @Autowired
    private ChildELRepository childRepository;

    /**
     * {@link Transactional} guarantees that I have an open Persistence Context (Hibernate session)
     * and I can fetch lazily the needed objects.
     *
     * The data is taken in two requests lazily. One for the Parent. After that the children are fetched lazily.
     */
    @Test
    @Transactional
    public void whenReadFromParentRepository_givenEagerParentFetchAndLazyChildFetch_shouldExecuteTwoSqlRequests() {
        printMessage("Parent Call");
        ParentEL parent = parentRepository.findById(1L).get();
        printMessage("Child Call");
        Set<ChildEL> children = parent.getChildren();
        assertEquals(5, children.size());
        printMessage("End of Calls");
    }

    // ToDo N+1 and log how this happens from the Parent To Child relationship
    // ToDo Entity Graph example or @Query
    /**
     * The data is taken in one SQL request eagerly. After that the parent needs to be initialized with
     * another SQL query as if we just took him from the database.
     */
    @Test
    @Transactional
    public void whenReadFromChildRepository_givenEagerParentFetchAndLazyChildFetch_shouldExecuteTwoSqlRequests() {
        printMessage("Child Call");
        ChildEL child = childRepository.findById(1L).get();
        printMessage("Parent Call");
        ParentEL parent = child.getParent();
        Set<ChildEL> children = parent.getChildren();
        assertEquals("Parent 1", parent.getName());
        assertEquals(5, children.size());
        printMessage("End of Call");
    }
}
