package com.example.springdata.entities.lazyeager.onetoone;

import javax.persistence.*;

@Entity
@Table(name = "children_l_l_o")
public class ChildLLO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String name;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private ParentLLO parent;

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

    public ParentLLO getParent() {
        return parent;
    }

    public void setParent(ParentLLO parent) {
        this.parent = parent;
    }
}
