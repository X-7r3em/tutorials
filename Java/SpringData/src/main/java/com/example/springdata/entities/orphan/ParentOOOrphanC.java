package com.example.springdata.entities.orphan;

import javax.persistence.*;

@Entity
@Table(name = "parents_oo_orphan_c")
public class ParentOOOrphanC {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String name;

    @OneToOne(mappedBy = "parent", fetch = FetchType.LAZY)
    private ChildOOOrphanC child;

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

    public ChildOOOrphanC getChild() {
        return child;
    }

    public void setChild(ChildOOOrphanC child) {
        this.child = child;
    }
}
