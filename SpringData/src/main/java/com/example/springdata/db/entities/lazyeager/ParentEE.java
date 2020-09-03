package com.example.springdata.db.entities.lazyeager;

import com.example.springdata.db.entities.Teacher;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "parent_e_e")
public class ParentEE {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String name;

    @OneToMany(mappedBy = "parents", fetch = FetchType.EAGER)
    private Set<ChildEE> children;
}
