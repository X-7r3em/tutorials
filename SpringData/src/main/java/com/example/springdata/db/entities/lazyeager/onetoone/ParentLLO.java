package com.example.springdata.db.entities.lazyeager.onetoone;

import javax.persistence.*;

@Entity
@Table(name = "parent_l_l_o")
public class ParentLLO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String name;

    @OneToOne(mappedBy = "parent", fetch = FetchType.LAZY)
    private ChildLLO children;

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

    public ChildLLO getChildren() {
        return children;
    }

    public void setChildren(ChildLLO children) {
        this.children = children;
    }
}
