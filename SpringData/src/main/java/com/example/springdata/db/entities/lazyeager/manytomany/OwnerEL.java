package com.example.springdata.db.entities.lazyeager.manytomany;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "owners_e_l")
public class OwnerEL {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String name;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "owners_slaves_e_l",
            joinColumns = @JoinColumn(name = "owner_id"),
            inverseJoinColumns = @JoinColumn(name = "slave_id")
    )
    private Set<SlaveEL> slaves;

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

    public Set<SlaveEL> getSlaves() {
        return slaves;
    }

    public void setSlaves(Set<SlaveEL> slaves) {
        this.slaves = slaves;
    }
}
