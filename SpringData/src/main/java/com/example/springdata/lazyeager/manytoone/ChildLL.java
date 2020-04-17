package com.example.springdata.lazyeager.manytoone;

import javax.persistence.*;

@Entity
@Table(name = "children_l_l")
public class ChildLL {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private ParentLL parent;

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

    public ParentLL getParent() {
        return parent;
    }

    public void setParent(ParentLL parent) {
        this.parent = parent;
    }
}
