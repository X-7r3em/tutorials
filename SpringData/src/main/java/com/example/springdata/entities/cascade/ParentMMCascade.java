package com.example.springdata.entities.cascade;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "parent_mm_cascade")
public class ParentMMCascade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String name;

    @ManyToMany(mappedBy = "parents", fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private Set<ChildMMCascade> children;

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

    public Set<ChildMMCascade> getChildren() {
        return children;
    }

    public void setChildren(Set<ChildMMCascade> children) {
        this.children = children;
    }

    @Override
    public String toString() {
        return "ParentMMCascade{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", children=" + children +
                '}';
    }
}
