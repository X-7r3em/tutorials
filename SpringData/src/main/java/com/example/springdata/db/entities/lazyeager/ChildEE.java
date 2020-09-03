package com.example.springdata.db.entities.lazyeager;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "child_e_e")
public class ChildEE {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String name;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "parent_id")
    private Set<ParentEE> parents;

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

    public Set<ParentEE> getParents() {
        return parents;
    }

    public void setParents(Set<ParentEE> parents) {
        this.parents = parents;
    }
}
