package com.example.springdata.entities.lazyeager.manytoone;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "parents_e_l")
public class ParentEL {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String name;

    @OneToMany(mappedBy = "parent", fetch = FetchType.LAZY)
    private Set<ChildEL> children;

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

    public Set<ChildEL> getChildren() {
        return children;
    }

    public void setChildren(Set<ChildEL> children) {
        this.children = children;
    }
}
