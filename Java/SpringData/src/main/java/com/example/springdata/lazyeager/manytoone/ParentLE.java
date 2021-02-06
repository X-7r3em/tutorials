package com.example.springdata.lazyeager.manytoone;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "parents_l_e")
public class ParentLE {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String name;

    @OneToMany(mappedBy = "parent", fetch = FetchType.EAGER)
    private Set<ChildLE> children;

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

    public Set<ChildLE> getChildren() {
        return children;
    }

    public void setChildren(Set<ChildLE> children) {
        this.children = children;
    }
}
