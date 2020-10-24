package com.example.springdata.cascade;

import com.example.springdata.entities.cascade.ChildMOCascade;
import com.example.springdata.entities.cascade.ParentMOCascade;
import com.example.springdata.repositories.cascade.ChildCascadeMORepository;
import com.example.springdata.repositories.cascade.ParentCascadeMORepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.HashSet;

@SpringBootTest
public class CascadeMOTest {
    @Autowired
    private ChildCascadeMORepository childCascadeMORepository;

    @Autowired
    private ParentCascadeMORepository parentCascadeMORepository;

    /**
     * Cascading does the selected option (Persist, Detach etc) on the children as well.
     * <p>
     * Cascade is dependant on which relationship is put. If put only on the one in the parent,
     * it will work only from parent, if put on the one in the child, it will work only from that relation.
     *
     * The child will be saved to the DB as there is a Cascade.PERSIST in the Parent of the relation.
     */
    @Test
    @Commit
    @Transactional
    public void persistParent_whenCascadePersistsInParent_willPersistChild() {
        ParentMOCascade parent = parentCascadeMORepository.findById(1L).get();

        ChildMOCascade child = new ChildMOCascade();
        child.setName("Child persist 1");
        child.setParent(parent);

        parent.getChildren().add(child);
    }

    /**
     * The child will be removed from the DB as there is a Cascade.REMOVE in the Parent of the relation.
     *
     * {@link javax.persistence.CascadeType} Remove is needed to remove the children from the parent.
     */
    @Test
    @Commit
    @Transactional
    public void removeParent_whenCascadeDeleteInParent_willRemoveChild() {
        parentCascadeMORepository.deleteById(2L);
    }

    /**
     * The parent will be saved to the DB as there is a Cascade.PERSIST in the Child of the relation.
     */
    @Test
    @Commit
    public void persistChild_whenCascadePersistsInChild_willPersistParent() {
        ParentMOCascade parent = new ParentMOCascade();
        parent.setName("Parent Cascade from child!");
        ChildMOCascade child = new ChildMOCascade();
        child.setName("Child that persisted parent");
        child.setParent(parent);
        parent.setChildren(new HashSet<>(Collections.singletonList(child)));

        childCascadeMORepository.saveAndFlush(child);
    }

    /**
     * The parent will be removed from the DB as there is a Cascade.REMOVE in the Child of the relation.
     */
    @Test
    @Commit
    @Transactional
    public void removeChild_whenCascadeDeleteInChild_willDeleteParent() {
        childCascadeMORepository.deleteById(3L);
    }

}
