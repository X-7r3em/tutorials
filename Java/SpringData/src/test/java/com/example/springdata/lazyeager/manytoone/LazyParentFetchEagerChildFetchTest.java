package com.example.springdata.lazyeager.manytoone;

import com.example.springdata.AbstractUnitTest;
import com.example.springdata.repositories.lazyeager.manytoone.ChildLERepository;
import com.example.springdata.repositories.lazyeager.manytoone.ParentLERepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class LazyParentFetchEagerChildFetchTest extends AbstractUnitTest {

    // Child Entity
    @Autowired
    private ParentLERepository parentRepository;

    // Parent Entity
    @Autowired
    private ChildLERepository childRepository;

    /**
     * The data is fetched eagerly in one request.
     */
    @Test
    public void whenReadFromParentRepository_givenLazyParentFetchAndEagerChildFetch_shouldExecuteOneSqlRequest() {
        printMessage("Parent Call");
        ParentLE parent = parentRepository.findById(1L).get();
        printMessage("Child Call");
        Set<ChildLE> children = parent.getChildren();
        assertEquals(2, children.size());
        printMessage("End of Calls");
    }

    /**
     * {@link Transactional} guarantees that I have an open Persistence Context (Hibernate session)
     * and I can fetch lazily the needed objects.
     *
     * The data is fetched with 2 SQL requests lazily. The first  reads the child information. The second retrieves
     * the parent.
     */
    @Test
    @Transactional
    public void whenReadFromChildRepository_givenLazyParentFetchAndEagerChildFetch_shouldExecuteTwoSqlRequests() {
        printMessage("Child Call");
        ChildLE child = childRepository.findById(1L).get();
        printMessage("Parent Call");
        ParentLE parent = child.getParent();
        assertEquals("Parent 1", parent.getName());
        printMessage("End of Call");
    }
}
