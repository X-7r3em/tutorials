package com.example.springdata.entities.cascade;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "child_mm_cascade")
public class ChildMMCascade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String name;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    @JoinTable(name = "child_parent_mm_cascade",
            joinColumns = @JoinColumn(name = "child_mm_cascade_id"),
            inverseJoinColumns = @JoinColumn(name = "parent_mm_cascade_id")
    )
    private Set<ParentMMCascade> parents;

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

    public Set<ParentMMCascade> getParents() {
        return parents;
    }

    public void setParents(Set<ParentMMCascade> parents) {
        this.parents = parents;
    }

    @Override
    public String toString() {
        return "ChildPersist{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", parent=" + parents +
                '}';
    }
}
