package com.example.springdata.entities.lazyeager.manytomany;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "owners_e_e")
public class OwnerEE {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String name;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "owners_slaves_e_e",
            joinColumns = @JoinColumn(name = "owner_id"),
            inverseJoinColumns = @JoinColumn(name = "slave_id")
    )
    private Set<SlaveEE> slaves;

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

    public Set<SlaveEE> getSlaves() {
        return slaves;
    }

    public void setSlaves(Set<SlaveEE> slaves) {
        this.slaves = slaves;
    }
}
