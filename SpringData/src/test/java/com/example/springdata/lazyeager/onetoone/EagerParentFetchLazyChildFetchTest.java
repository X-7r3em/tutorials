package com.example.springdata.lazyeager.onetoone;

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
     * The data is fetched in a at the same time, even though it should be lazily fetched. This is because
     * Hibernate needs to enquire the child table to see if it will be null, or a proxy. So instead of
     * just checking, it pulls the data as well. This is only when requesting from a Parent entity in a
     * {@link javax.persistence.OneToOne} relationship.
     *
     * Note: In some versions of Hibernate, optional = false may alter this behaviour.
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
