package com.example.springdata.lazyeager.manytomany;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "slaves_l_e")
public class SlaveLE {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String name;

    @ManyToMany(mappedBy = "slaves", fetch = FetchType.EAGER)
    private Set<OwnerLE> owners;

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

    public Set<OwnerLE> getOwners() {
        return owners;
    }

    public void setOwners(Set<OwnerLE> owners) {
        this.owners = owners;
    }
}
