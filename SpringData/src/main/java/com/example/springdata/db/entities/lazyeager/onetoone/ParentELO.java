package com.example.springdata.db.entities.lazyeager.onetoone;

import javax.persistence.*;

@Entity
@Table(name = "parents_e_l_o")
public class ParentELO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String name;

    @OneToOne(mappedBy = "parent", fetch = FetchType.LAZY)
    private ChildELO children;

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

    public ChildELO getChildren() {
        return children;
    }

    public void setChildren(ChildELO children) {
        this.children = children;
    }
}
