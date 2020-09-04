package com.example.springdata.db.entities.lazyeager.manytoone;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "parents_l_l")
public class ParentLL {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String name;

    @OneToMany(mappedBy = "parent", fetch = FetchType.LAZY)
    private Set<ChildLL> children;

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

    public Set<ChildLL> getChildren() {
        return children;
    }

    public void setChildren(Set<ChildLL> children) {
        this.children = children;
    }
}
