package com.example.springdata.entities.orphan;

import javax.persistence.*;

@Entity
@Table(name = "children_oo_orphan_p")
public class ChildOOOrphanP {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String name;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private ParentOOOrphanP parent;

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

    public ParentOOOrphanP getParent() {
        return parent;
    }

    public void setParent(ParentOOOrphanP parent) {
        this.parent = parent;
    }
}
