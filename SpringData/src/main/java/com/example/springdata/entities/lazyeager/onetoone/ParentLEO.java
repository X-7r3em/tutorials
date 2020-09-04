package com.example.springdata.entities.lazyeager.onetoone;

import javax.persistence.*;

@Entity
@Table(name = "parents_l_e_o")
public class ParentLEO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String name;

    @OneToOne(mappedBy = "parent", fetch = FetchType.EAGER)
    private ChildLEO children;

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

    public ChildLEO getChildren() {
        return children;
    }

    public void setChildren(ChildLEO children) {
        this.children = children;
    }
}
