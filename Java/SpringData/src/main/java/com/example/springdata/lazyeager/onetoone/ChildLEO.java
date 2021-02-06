package com.example.springdata.lazyeager.onetoone;

import javax.persistence.*;

@Entity
@Table(name = "children_l_e_o")
public class ChildLEO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String name;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private ParentLEO parent;

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

    public ParentLEO getParent() {
        return parent;
    }

    public void setParent(ParentLEO parent) {
        this.parent = parent;
    }
}
