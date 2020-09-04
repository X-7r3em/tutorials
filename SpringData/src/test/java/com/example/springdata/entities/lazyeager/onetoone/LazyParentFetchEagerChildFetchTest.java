package com.example.springdata.entities.lazyeager.onetoone;

import com.example.springdata.AbstractUnitTest;
import com.example.springdata.repositories.lazyeager.onetoone.ChildLEORepository;
import com.example.springdata.repositories.lazyeager.onetoone.ParentLEORepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class LazyParentFetchEagerChildFetchTest extends AbstractUnitTest {

    // Child Entity
    @Autowired
    private ParentLEORepository parentRepository;

    // Parent Entity
    @Autowired
    private ChildLEORepository childRepository;


    @Test
    public void whenReadFromParentRepository_givenLazyParentFetchAndEagerChildFetch_shouldExecuteOneSqlRequest() {
        printMessage("Parent Call");
        ParentLEO parent = parentRepository.findById(1L).get();
        printMessage("Child Call");
        ChildLEO child = parent.getChildren();
        assertEquals("Child 1", child.getName());
        printMessage("End of Calls");
    }

    /**
     * {@link Transactional} guarantees that I have an open Persistence Context (Hibernate session)
     * and I can fetch lazily the needed objects.
     */
    @Test
    @Transactional
    public void whenReadFromChildRepository_givenLazyParentFetchAndEagerChildFetch_shouldExecuteTwoSqlRequests() {
        printMessage("Child Call");
        ChildLEO child = childRepository.findById(1L).get();
        printMessage("Parent Call");
        ParentLEO parent = child.getParent();
        assertEquals("Parent 1", parent.getName());
        printMessage("End of Call");
    }
}
