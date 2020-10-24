package com.example.springdata.lazyeager.manytoone;

import com.example.springdata.AbstractUnitTest;
import com.example.springdata.repositories.lazyeager.manytoone.ChildEERepository;
import com.example.springdata.repositories.lazyeager.manytoone.ParentEERepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class EagerParentFetchEagerChildFetchTest extends AbstractUnitTest {

    // Child Entity
    @Autowired
    private ParentEERepository parentRepository;

    // Parent Entity
    @Autowired
    private ChildEERepository childRepository;

    /**
     * {@link Transactional} is not needed as all the entities are eagerly loaded in the same session.
     *
     * The data is taken in one SQL request eagerly with a join.
     */
    @Test
    public void whenReadFromParentRepository_givenEagerParentFetchAndEagerChildFetch_shouldExecuteOneSqlRequest() {
        printMessage("Parent Call");
        ParentEE parent = parentRepository.findById(1L).get();
        printMessage("Child Call");
        Set<ChildEE> children = parent.getChildren();
        assertEquals(2, children.size());
        printMessage("End of Calls");
    }

    /**
     * The data is taken in two SQL requests eagerly. The first takes the Child and its Parent information.
     * Then for the parent to be created, all his children need to be taken with the second SQL request.
     */
    @Test
    public void whenReadFromChildRepository_givenEagerParentFetchAndEagerChildFetch_shouldExecuteTwoSqlRequests() {
        printMessage("Child Call");
        ChildEE child = childRepository.findById(1L).get();
        printMessage("Parent Call");
        ParentEE parent = child.getParent();
        assertEquals("Parent 1", parent.getName());
        printMessage("End of Call");
    }
}
