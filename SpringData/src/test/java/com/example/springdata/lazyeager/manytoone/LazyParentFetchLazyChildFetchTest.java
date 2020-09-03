package com.example.springdata.lazyeager.manytoone;

import com.example.springdata.AbstractUnitTest;
import com.example.springdata.db.entities.lazyeager.manytoone.ChildLL;
import com.example.springdata.db.entities.lazyeager.manytoone.ParentLL;
import com.example.springdata.db.repositories.lazyeager.manytoone.ChildLLRepository;
import com.example.springdata.db.repositories.lazyeager.manytoone.ParentLLRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class LazyParentFetchLazyChildFetchTest extends AbstractUnitTest {

    // Child Entity
    @Autowired
    private ParentLLRepository parentRepository;

    // Parent Entity
    @Autowired
    private ChildLLRepository childRepository;

    /**
     * {@link Transactional} guarantees that I have an open Persistence Context (Hibernate session)
     * and I can fetch lazily the needed objects.
     */
    @Test
    @Transactional
    public void whenReadFromParentRepository_givenLazyParentFetchAndLazyChildFetch_shouldExecuteTwoSqlRequests() {
        printMessage("Parent Call");
        ParentLL parent = parentRepository.findById(1L).get();
        printMessage("Child Call");
        Set<ChildLL> children = parent.getChildren();
        assertEquals(2, children.size());
        printMessage("End of Calls");
    }

    @Test
    @Transactional
    public void whenReadFromChildRepository_givenLazyParentFetchAndLazyChildFetch_shouldExecuteTwoSqlRequests() {
        printMessage("Child Call");
        ChildLL child = childRepository.findById(1L).get();
        printMessage("Parent Call");
        ParentLL parent = child.getParent();
        assertEquals("Parent 1", parent.getName());
        printMessage("End of Call");
    }
}
