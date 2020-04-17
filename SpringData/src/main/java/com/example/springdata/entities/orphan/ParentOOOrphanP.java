package com.example.springdata.entities.orphan;

import javax.persistence.*;

@Entity
@Table(name = "parents_oo_orphan_p")
public class ParentOOOrphanP {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String name;

    @OneToOne(mappedBy = "parent", fetch = FetchType.LAZY, orphanRemoval = true)
    private ChildOOOrphanP child;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ChildOOOrphanP getChild() {
        return child;
    }

    public void setChild(ChildOOOrphanP child) {
        this.child = child;
    }
}
