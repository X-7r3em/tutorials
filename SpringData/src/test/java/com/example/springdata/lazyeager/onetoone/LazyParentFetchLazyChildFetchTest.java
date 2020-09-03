package com.example.springdata.lazyeager.onetoone;

import com.example.springdata.AbstractUnitTest;
import com.example.springdata.db.entities.lazyeager.onetoone.ChildLLO;
import com.example.springdata.db.entities.lazyeager.onetoone.ParentLLO;
import com.example.springdata.db.repositories.lazyeager.onetoone.ChildLLORepository;
import com.example.springdata.db.repositories.lazyeager.onetoone.ParentLLORepository;
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
    private ParentLLORepository parentRepository;

    // Parent Entity
    @Autowired
    private ChildLLORepository childRepository;

    /**
     * {@link Transactional} guarantees that I have an open Persistence Context (Hibernate session)
     * and I can fetch lazily the needed objects.
     */
    @Test
    @Transactional
    public void whenReadFromParentRepository_givenLazyParentFetchAndLazyChildFetch_shouldExecuteTwoSqlRequests() {
        printMessage("Parent Call");
        ParentLLO parent = parentRepository.findById(1L).get();
        printMessage("Child Call");
        ChildLLO children = parent.getChildren();
        assertEquals("Child 1", children.getName());
        printMessage("End of Calls");
    }

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
