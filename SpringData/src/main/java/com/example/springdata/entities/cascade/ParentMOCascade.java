package com.example.springdata.entities.cascade;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "parents_mo_cascade")
public class ParentMOCascade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String name;

    @OneToMany(mappedBy = "parent", fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private Set<ChildMOCascade> children;

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

    public Set<ChildMOCascade> getChildren() {
        return children;
    }

    public void setChildren(Set<ChildMOCascade> children) {
        this.children = children;
    }

    @Override
    public String toString() {
        return "ParentMOCascade{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", children=" + children +
                '}';
    }
}
