package com.example.springdata.entities.lazyeager.onetoone;

import javax.persistence.*;

@Entity
@Table(name = "parents_l_l_o")
public class ParentLLO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String name;

    @OneToOne(mappedBy = "parent", fetch = FetchType.LAZY)
    private ChildLLO child;

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

    public ChildLLO getChild() {
        return child;
    }

    public void setChild(ChildLLO children) {
        this.child = children;
    }
}
