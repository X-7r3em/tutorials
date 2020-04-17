package com.example.springdata.orphan;

import com.example.springdata.entities.orphan.*;
import com.example.springdata.repositories.orphan.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
public class OrphanTest {
    @Autowired
    private ParentOOOrphanPRepository parentOOOrphanPRepository;

    @Autowired
    private ChildOOOrphanCRepository childOOOrphanCRepository;

    @Autowired
    private ParentOMOrphanRepository parentOMOrphanRepository;

    @Autowired
    private ChildOMOrphanRepository childOMOrphanRepository;

    /**
     * Orphan Child will remove the child when it does not have a parent any more, meaning when
     * it does not refer to any parent
     */
    @Test
    @Commit
    @Transactional
    public void childRemovalOO_whenOrphanRemovalInParent_willDeleteChild() {
        ParentOOOrphanP parent = parentOOOrphanPRepository.findById(1L).get();
        parent.setChild(null);
    }

    /**
     * In 1 to 1 it works both ways when orphaning a parent as well.
     */
    @Test
    @Commit
    @Transactional
    public void childRemovalOO_whenOrphanRemovalInChild_willDeleteChild() {
        ChildOOOrphanC child = childOOOrphanCRepository.findById(2L).get();
        child.setParent(null);
    }

    /**
     * Will remove children without parents. Requires the Cascade Remove option on the Parent relation.
     */
    @Test
    @Commit
    @Transactional
    public void childRemovalOM_whenOrphanRemoval_willDeleteChild() {
        ParentOMOrphan parent = parentOMOrphanRepository.findById(1L).get();
        parent.getChildren().clear();
    }

}
