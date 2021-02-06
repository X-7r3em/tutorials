package com.example.springdata.entities.orphan;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "parents_om_orphan")
public class ParentOMOrphan {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String name;

    @OneToMany(mappedBy = "parent", fetch = FetchType.LAZY, orphanRemoval = true, cascade = CascadeType.ALL)
    private Set<ChildOMOrphan> children;

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

    public Set<ChildOMOrphan> getChildren() {
        return children;
    }

    public void setChildren(Set<ChildOMOrphan> children) {
        this.children = children;
    }
}
