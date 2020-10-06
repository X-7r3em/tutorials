package com.example.springdata.cascade;

import com.example.springdata.entities.cascade.ChildMMCascade;
import com.example.springdata.entities.cascade.ParentMMCascade;
import com.example.springdata.repositories.cascade.ChildCascadeMMRepository;
import com.example.springdata.repositories.cascade.ParentCascadeMMRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.HashSet;

@SpringBootTest
public class CascadeMMTest {
    @Autowired
    private ChildCascadeMMRepository childCascadeMMRepository;

    @Autowired
    private ParentCascadeMMRepository parentCascadeMMRepository;

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
        ParentMMCascade parent = parentCascadeMMRepository.findById(1L).get();

        ChildMMCascade child = new ChildMMCascade();
        child.setName("Child persisted from Parent");
        child.setParents(new HashSet<>(Collections.singletonList(parent)));

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
        parentCascadeMMRepository.deleteById(2L);
    }

    /**
     * The parent will be saved to the DB as there is a Cascade.PERSIST in the Child of the relation.
     */
    @Test
    @Commit
    public void persistChild_whenCascadePersistsInChild_willPersistParent() {
        ParentMMCascade parent = new ParentMMCascade();
        parent.setName("Parent Cascade from child!");
        ChildMMCascade child = new ChildMMCascade();
        child.setName("Child that persisted parent");
        child.setParents(new HashSet<>(Collections.singletonList(parent)));
        parent.setChildren(new HashSet<>(Collections.singletonList(child)));

        childCascadeMMRepository.saveAndFlush(child);
    }

    /**
     * The parent will be removed from the DB as there is a Cascade.REMOVE in the Child of the relation.
     */
    @Test
    @Commit
    @Transactional
    public void removeChild_whenCascadeDeleteInChild_willDeleteParent() {
        childCascadeMMRepository.deleteById(3L);
    }

    /**
     * Multiple entities will be removed from the DB as there is a Cascade.REMOVE in the Child and Parent. This will
     * cause a chain deletion of all related entities. Every entity from 5-7 will be deleted on both sides.
     */
    @Test
    @Commit
    @Transactional
    public void removeChild_whenCascadeDeleteInChildAndParentAndChainedEntities_willDeleteChainedParentsAndChildren() {
        childCascadeMMRepository.deleteById(5L);
    }

}
