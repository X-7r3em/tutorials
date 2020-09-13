package com.example.springdata.entities.cascade;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "parent_oo_cascade")
public class ParentOOCascade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String name;

    @OneToOne(mappedBy = "parent", fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private ChildOOCascade child;

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

    public ChildOOCascade getChild() {
        return child;
    }

    public void setChild(ChildOOCascade child) {
        this.child = child;
    }

    @Override
    public String toString() {
        return "ParentOOCascade{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", child=" + child +
                '}';
    }
}
