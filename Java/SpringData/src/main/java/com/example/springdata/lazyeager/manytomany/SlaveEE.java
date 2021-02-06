package com.example.springdata.lazyeager.manytomany;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "slaves_e_e")
public class SlaveEE {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String name;

    @ManyToMany(mappedBy = "slaves", fetch = FetchType.EAGER)
    private Set<OwnerEE> owners;

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

    public Set<OwnerEE> getOwners() {
        return owners;
    }

    public void setOwners(Set<OwnerEE> owners) {
        this.owners = owners;
    }
}
