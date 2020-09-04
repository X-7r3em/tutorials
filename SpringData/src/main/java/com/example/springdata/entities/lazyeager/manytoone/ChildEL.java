package com.example.springdata.entities.lazyeager.manytoone;

import javax.persistence.*;

@Entity
@Table(name = "children_e_l")
public class ChildEL {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String name;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "parent_id")
    private ParentEL parent;

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

    public ParentEL getParent() {
        return parent;
    }

    public void setParent(ParentEL parent) {
        this.parent = parent;
    }
}
