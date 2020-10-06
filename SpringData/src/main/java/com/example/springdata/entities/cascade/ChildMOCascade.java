package com.example.springdata.entities.cascade;

import javax.persistence.*;

@Entity
@Table(name = "child_mo_cascade")
public class ChildMOCascade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String name;

    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    @JoinColumn(name = "parent_mo_cascade_id")
    private ParentMOCascade parent;

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

    public ParentMOCascade getParent() {
        return parent;
    }

    public void setParent(ParentMOCascade parent) {
        this.parent = parent;
    }

    @Override
    public String toString() {
        return "ChildMOCascade{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", parent=" + parent +
                '}';
    }
}
