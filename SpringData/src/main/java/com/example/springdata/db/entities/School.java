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


    @OneToMany(mappedBy = "school")
    private Set<Person> teachers;

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

    public Set<Person> getTeachers() {
        return teachers;
    }

    public void setTeachers(Set<Person> teachers) {
        this.teachers = teachers;
    }
}
