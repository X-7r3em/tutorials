package com.example.springdata.entities.orphan;

import javax.persistence.*;

@Entity
@Table(name = "children_oo_orphan_c")
public class ChildOOOrphanC {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String name;

    @OneToOne(fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinColumn(name = "parent_id")
    private ParentOOOrphanC parent;

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

    public ParentOOOrphanC getParent() {
        return parent;
    }

    public void setParent(ParentOOOrphanC parent) {
        this.parent = parent;
    }
}
