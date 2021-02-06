package com.example.springdata.lazyeager.onetoone;

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
     * The data is fetched in a at the same time, even though it should be lazily fetched. This is because
     * Hibernate needs to enquire the child table to see if it will be null, or a proxy. So instead of
     * just checking, it pulls the data as well. This is only when requesting from a Parent entity in a
     * {@link javax.persistence.OneToOne} relationship.
     *
     * Note: In some versions of Hibernate, optional = false may alter this behaviour.
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
