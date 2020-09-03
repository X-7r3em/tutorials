package com.example.springdata.db.entities.lazyeager.manytoone;

import javax.persistence.*;

@Entity
@Table(name = "child_e_e")
public class ChildEE {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String name;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "parent_id")
    private ParentEE parent;

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

    public ParentEE getParent() {
        return parent;
    }

    public void setParent(ParentEE parent) {
        this.parent = parent;
    }
}
