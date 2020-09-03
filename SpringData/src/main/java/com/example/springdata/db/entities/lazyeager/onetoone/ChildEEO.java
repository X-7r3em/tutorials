package com.example.springdata.db.entities.lazyeager.onetoone;

import javax.persistence.*;

@Entity
@Table(name = "child_e_e_o")
public class ChildEEO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String name;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "parent_id")
    private ParentEEO parent;

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

    public ParentEEO getParent() {
        return parent;
    }

    public void setParent(ParentEEO parent) {
        this.parent = parent;
    }
}
