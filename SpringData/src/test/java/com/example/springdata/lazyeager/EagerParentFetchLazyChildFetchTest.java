package com.example.springdata.lazyeager;

import com.example.springdata.AbstractUnitTest;
import com.example.springdata.db.entities.lazyeager.ChildEL;
import com.example.springdata.db.entities.lazyeager.ParentEL;
import com.example.springdata.db.repositories.lazyeager.ChildELRepository;
import com.example.springdata.db.repositories.lazyeager.ParentELRepository;
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
     */
    @Test
    @Transactional
    public void whenReadFromParentRepository_givenEagerParentFetchAndLazyChildFetch_shouldExecuteTwoSqlRequests() {
        printMessage("Parent Call");
        ParentEL parent = parentRepository.findById(1L).get();
        printMessage("Child Call");
        Set<ChildEL> children = parent.getChildren();
        assertEquals(2, children.size());
        printMessage("End of Calls");
    }

    @Test
    public void whenReadFromChildRepository_givenEagerParentFetchAndLazyChildFetch_shouldExecuteOneSqlRequest() {
        printMessage("Child Call");
        ChildEL child = childRepository.findById(1L).get();
        printMessage("Parent Call");
        ParentEL parent = child.getParent();
        assertEquals("Parent 1", parent.getName());
        printMessage("End of Call");
    }
}
