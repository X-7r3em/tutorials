package com.example.springdata.lazyeager.onetoone;

import com.example.springdata.AbstractUnitTest;
import com.example.springdata.repositories.lazyeager.onetoone.ChildEEORepository;
import com.example.springdata.repositories.lazyeager.onetoone.ParentEEORepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class EagerParentFetchEagerChildFetchTest extends AbstractUnitTest {

    // Child Entity
    @Autowired
    private ParentEEORepository parentRepository;

    // Parent Entity
    @Autowired
    private ChildEEORepository childRepository;

    /**
     * {@link Transactional} is not needed as all the entities are eagerly loaded in the same session.
     * <p>
     * The request is done in one SQL query eagerly.
     */
    @Test
    public void whenReadFromParentRepository_givenEagerParentFetchAndEagerChildFetch_shouldExecuteASingleSqlRequest() {
        printMessage("Parent Call");
        ParentEEO parent = parentRepository.findById(1L).get();
        printMessage("Child Call");
        ChildEEO child = parent.getChild();
        assertEquals("Child 1", child.getName());
        printMessage("End of Calls");
    }

    /**
     * The request is done in one SQL query eagerly.
     */
    @Test
    public void whenReadFromChildRepository_givenEagerParentFetchAndEagerChildFetch_shouldExecuteOneSqlRequest() {
        printMessage("Child Call");
        ChildEEO child = childRepository.findById(1L).get();
        printMessage("Parent Call");
        ParentEEO parent = child.getParent();
        assertEquals("Parent 1", parent.getName());
        printMessage("End of Call");
    }
}
