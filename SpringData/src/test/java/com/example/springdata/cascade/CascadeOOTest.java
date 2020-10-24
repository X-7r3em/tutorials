package com.example.springdata.cascade;

import com.example.springdata.entities.cascade.ChildOOCascade;
import com.example.springdata.entities.cascade.ParentOOCascade;
import com.example.springdata.repositories.cascade.ChildCascadeOORepository;
import com.example.springdata.repositories.cascade.ParentCascadeOORepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
public class CascadeOOTest {
    @Autowired
    private ChildCascadeOORepository childCascadeOORepository;

    @Autowired
    private ParentCascadeOORepository parentCascadeOORepository;

    /**
     * The child will be saved to the DB as there is a Cascade.PERSIST in the Parent of the relation.
     */
    @Test
    @Commit
    @Transactional
    public void persistParent_whenCascadePersistsInParent_willPersistChild() {
        ParentOOCascade parent = parentCascadeOORepository.findById(4L).get();

        ChildOOCascade child = new ChildOOCascade();
        child.setName("Child that is persisted");
        child.setParent(parent);

        parent.setChild(child);
    }

    /**
     * The child will be removed from the DB as there is a Cascade.REMOVE in the Parent of the relation.
     */
    @Test
    @Commit
    @Transactional
    public void removeParent_whenCascadeDeleteInParent_willRemoveChild() {
        parentCascadeOORepository.deleteById(2L);
    }

    /**
     * The parent will be saved to the DB as there is a Cascade.PERSIST in the Child of the relation.
     */
    @Test
    @Commit
    public void persistChild_whenCascadePersistsInChild_willPersistParent() {
        ParentOOCascade parent = new ParentOOCascade();
        parent.setName("Parent Cascade from child!");
        ChildOOCascade child = new ChildOOCascade();
        child.setName("Child that persisted parent");
        child.setParent(parent);
        parent.setChild(child);

        childCascadeOORepository.saveAndFlush(child);
    }

    /**
     * The parent will be removed from the DB as there is a Cascade.REMOVE in the Child of the relation.
     */
    @Test
    @Commit
    @Transactional
    public void removeChild_whenCascadeDeleteInChild_willDeleteParent() {
        childCascadeOORepository.deleteById(3L);
    }

}
