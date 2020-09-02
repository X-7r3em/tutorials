package com.example.springdata.db.entities;

import javax.persistence.*;

@Entity(name = "teachers")
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column
    private String name;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "school_id_eager_lazy")
    private School schoolEagerLazy;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "school_id_lazy_lazy")
    private School schoolLazyLazy;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "school_id_eager_eager")
    private School schoolEagerEager;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "school_id_lazy_eager")
    private School schoolLazyEager;

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

    public School getSchoolEagerLazy() {
        return schoolEagerLazy;
    }

    public void setSchoolEagerLazy(School schoolEagerLazy) {
        this.schoolEagerLazy = schoolEagerLazy;
    }

    public School getSchoolLazyLazy() {
        return schoolLazyLazy;
    }

    public void setSchoolLazyLazy(School schoolLazyLazy) {
        this.schoolLazyLazy = schoolLazyLazy;
    }

    public School getSchoolEagerEager() {
        return schoolEagerEager;
    }

    public void setSchoolEagerEager(School schoolEagerEager) {
        this.schoolEagerEager = schoolEagerEager;
    }

    public School getSchoolLazyEager() {
        return schoolLazyEager;
    }

    public void setSchoolLazyEager(School schoolLazyEager) {
        this.schoolLazyEager = schoolLazyEager;
    }
}
