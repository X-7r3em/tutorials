package com.example.validation.dto;

import com.example.validation.annotations.OnCreate;
import com.example.validation.annotations.OnUpdate;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

public class GroupContainer {
    @Null(groups = OnCreate.class, message = "I must be empty on create")
    @NotNull(groups = OnUpdate.class, message = "I must not be empty on update")
    private String name;

    public GroupContainer() {
    }

    public GroupContainer(@Null(groups = OnCreate.class, message = "I must be empty on create") @NotNull(groups = OnUpdate.class, message = "I must not be empty on update") String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "GroupContainer{" +
                "name='" + name + '\'' +
                '}';
    }
}
