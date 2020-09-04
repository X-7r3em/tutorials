package com.example.springdata.entities.lazyeager.manytomany;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "slaves_e_l")
public class SlaveEL {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String name;

    @ManyToMany(mappedBy = "slaves", fetch = FetchType.LAZY)
    private Set<OwnerEL> owners;

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

    public Set<OwnerEL> getOwners() {
        return owners;
    }

    public void setOwners(Set<OwnerEL> owners) {
        this.owners = owners;
    }
}
