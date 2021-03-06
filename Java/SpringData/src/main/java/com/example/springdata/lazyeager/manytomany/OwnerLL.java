package com.example.springdata.lazyeager.manytomany;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "owners_l_l")
public class OwnerLL {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String name;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "owners_slaves_l_l",
            joinColumns = @JoinColumn(name = "owner_id"),
            inverseJoinColumns = @JoinColumn(name = "slave_id")
    )
    private Set<SlaveLL> slaves;

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

    public Set<SlaveLL> getSlaves() {
        return slaves;
    }

    public void setSlaves(Set<SlaveLL> slaves) {
        this.slaves = slaves;
    }
}
