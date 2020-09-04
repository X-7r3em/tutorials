package com.example.springdata.entities.lazyeager.manytoone;

import javax.persistence.*;

@Entity
@Table(name = "children_l_e")
public class ChildLE {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private ParentLE parent;

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

    public ParentLE getParent() {
        return parent;
    }

    public void setParent(ParentLE parent) {
        this.parent = parent;
    }
}
