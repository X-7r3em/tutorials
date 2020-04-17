package com.example.springdata.entities.cascade;

import javax.persistence.*;

@Entity
@Table(name = "children_oo_cascade")
public class ChildOOCascade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String name;

    @OneToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    @JoinColumn(name = "parent_oo_cascade_id", unique = true) /* unique is needed or else it is a ManyToOne */
    private ParentOOCascade parent;

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

    public ParentOOCascade getParent() {
        return parent;
    }

    public void setParent(ParentOOCascade parent) {
        this.parent = parent;
    }

    @Override
    public String toString() {
        return "ChildOOCascade{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", parent=" + parent +
                '}';
    }
}
