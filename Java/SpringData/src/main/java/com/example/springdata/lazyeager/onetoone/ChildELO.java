package com.example.springdata.lazyeager.onetoone;

import javax.persistence.*;

@Entity
@Table(name = "children_e_l_o")
public class ChildELO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String name;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "parent_id")
    private ParentELO parent;

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

    public ParentELO getParent() {
        return parent;
    }

    public void setParent(ParentELO parent) {
        this.parent = parent;
    }
}
