package com.example.springdata.db.entities;

import javax.persistence.*;
import java.util.Set;

@Entity(name = "schools")
public class School {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String name;

    @OneToMany(mappedBy = "schoolEagerLazy", fetch = FetchType.LAZY)
    private Set<Teacher> teachersEagerLazy;

    @OneToMany(mappedBy = "schoolLazyLazy", fetch = FetchType.LAZY)
    private Set<Teacher> teachersLazyLazy;

    @OneToMany(mappedBy = "schoolEagerEager", fetch = FetchType.EAGER)
    private Set<Teacher> teachersEagerEager;

    @OneToMany(mappedBy = "schoolLazyEager", fetch = FetchType.EAGER)
    private Set<Teacher> teachersLazyEager;

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

    public Set<Teacher> getTeachersEagerLazy() {
        return teachersEagerLazy;
    }

    public void setTeachersEagerLazy(Set<Teacher> teachersEagerLazy) {
        this.teachersEagerLazy = teachersEagerLazy;
    }

    public Set<Teacher> getTeachersLazyLazy() {
        return teachersLazyLazy;
    }

    public void setTeachersLazyLazy(Set<Teacher> teachersLazyLazy) {
        this.teachersLazyLazy = teachersLazyLazy;
    }

    public Set<Teacher> getTeachersEagerEager() {
        return teachersEagerEager;
    }

    public void setTeachersEagerEager(Set<Teacher> teachersEagerEager) {
        this.teachersEagerEager = teachersEagerEager;
    }

    public Set<Teacher> getTeachersLazyEager() {
        return teachersLazyEager;
    }

    public void setTeachersLazyEager(Set<Teacher> teachersLazyEager) {
        this.teachersLazyEager = teachersLazyEager;
    }
}
