package com.example.springdata.lazyeager;

import com.example.springdata.AbstractUnitTest;
import com.example.springdata.db.entities.lazyeager.ChildEE;
import com.example.springdata.db.entities.lazyeager.ParentEE;
import com.example.springdata.db.repositories.lazyeager.ChildEERepository;
import com.example.springdata.db.repositories.lazyeager.ParentEERepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class EagerParentFetchEagerChildFetchTest extends AbstractUnitTest {

    // Child Entity
    @Autowired
    private ParentEERepository parentRepository;

    // Parent Entity
    @Autowired
    private ChildEERepository childRepository;

    @BeforeEach
    public void setup() {
    }

    /**
     * {@link Transactional} guarantees that I have an open Persistence Context (Hibernate session)
     * and I can fetch lazily the needed objects.
     */

    /**
     * {@link Transactional} is not needed as all the entities are eagerly loaded in the same session.
     */
    @Test
    public void whenReadFromParentRepository_givenEagerParentFetchAndEagerChildFetch_shouldExecuteASingleSqlRequest() {
        printMessage("Parent Call");
        ParentEE parent = parentRepository.findById(1L).get();
        printMessage("Child Call");
        Set<ChildEE> children = parent.getChildren();
        printMessage("End of Calls");
        assertEquals(2, children.size());
    }

    @Test
    public void whenReadFromChildRepository_givenEagerParentFetchAndEagerChildFetch_shouldExecuteTwoSqlRequests() {
        printMessage("Child Call");
        ChildEE child = childRepository.findById(1L).get();
        printMessage("Parent Call");
        ParentEE parent = child.getParent();
        printMessage("End of Call");
        assertEquals("Parent 1", parent.getName());
    }
}
