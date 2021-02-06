package com.example.springdata.lazyeager.manytoone;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "parents_e_e")
public class ParentEE {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String name;

    @OneToMany(mappedBy = "parent", fetch = FetchType.EAGER)
    private Set<ChildEE> children;

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

    public Set<ChildEE> getChildren() {
        return children;
    }

    public void setChildren(Set<ChildEE> children) {
        this.children = children;
    }
}
