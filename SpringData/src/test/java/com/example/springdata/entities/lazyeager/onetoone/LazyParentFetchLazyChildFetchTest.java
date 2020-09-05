package com.example.springdata.entities.lazyeager.onetoone;

import com.example.springdata.AbstractUnitTest;
import com.example.springdata.repositories.lazyeager.onetoone.ChildLLORepository;
import com.example.springdata.repositories.lazyeager.onetoone.ParentLLORepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class LazyParentFetchLazyChildFetchTest extends AbstractUnitTest {

    // Child Entity
    @Autowired
    private ParentLLORepository parentRepository;

    // Parent Entity
    @Autowired
    private ChildLLORepository childRepository;

    /**
     * {@link Transactional} guarantees that I have an open Persistence Context (Hibernate session)
     * and I can fetch lazily the needed objects.
     *
     * The data is taken in 1 request even though specified to be lazy.
     */
    @Test
    @Transactional
    public void whenReadFromParentRepository_givenLazyParentFetchAndLazyChildFetch_shouldExecuteTwoSqlRequests() {
        printMessage("Parent Call");
        ParentLLO parent = parentRepository.findById(1L).get();
        printMessage("Child Call");
        ChildLLO child = parent.getChild();
        assertEquals("Child 1", child.getName());
        printMessage("End of Calls");
    }

    /**
     * The data is gathered in two requests lazily.
     */
    @Test
    @Transactional
    public void whenReadFromChildRepository_givenLazyParentFetchAndLazyChildFetch_shouldExecuteTwoSqlRequests() {
        printMessage("Child Call");
        ChildLLO child = childRepository.findById(1L).get();
        printMessage("Parent Call");
        ParentLLO parent = child.getParent();
        assertEquals("Parent 1", parent.getName());
        printMessage("End of Call");
    }
}
