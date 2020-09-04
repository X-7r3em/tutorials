package com.example.springdata.db.entities.lazyeager.manytomany;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "slaves_l_l")
public class SlaveLL {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String name;

    @ManyToMany(mappedBy = "slaves", fetch = FetchType.EAGER)
    private Set<OwnerLL> owners;

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

    public Set<OwnerLL> getOwners() {
        return owners;
    }

    public void setOwners(Set<OwnerLL> owners) {
        this.owners = owners;
    }
}
