package com.example.springdata.entities.lazyeager.onetoone;

import com.example.springdata.AbstractUnitTest;
import com.example.springdata.repositories.lazyeager.onetoone.ChildELORepository;
import com.example.springdata.repositories.lazyeager.onetoone.ParentELORepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class EagerParentFetchLazyChildFetchTest extends AbstractUnitTest {

    // Child Entity
    @Autowired
    private ParentELORepository parentRepository;

    // Parent Entity
    @Autowired
    private ChildELORepository childRepository;

    /**
     * {@link Transactional} guarantees that I have an open Persistence Context (Hibernate session)
     * and I can fetch lazily the needed objects.
     *
     * The data is fetched in a single query, even though it should be lazily fetched.
     */
    @Test
    public void whenReadFromParentRepository_givenEagerParentFetchAndLazyChildFetch_shouldExecuteTwoSqlRequests() {
        printMessage("Parent Call");
        ParentELO parent = parentRepository.findById(1L).get();
        printMessage("Child Call");
        ChildELO child = parent.getChild();
        assertEquals("Child 1", child.getName());
        printMessage("End of Calls");
    }

    /**
     * The data is fetched in one query eagerly.
     */
    @Test
    public void whenReadFromChildRepository_givenEagerParentFetchAndLazyChildFetch_shouldExecuteOneSqlRequest() {
        printMessage("Child Call");
        ChildELO child = childRepository.findById(1L).get();
        printMessage("Parent Call");
        ParentELO parent = child.getParent();
        assertEquals("Parent 1", parent.getName());
        printMessage("End of Call");
    }
}
