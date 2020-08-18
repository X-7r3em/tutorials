package com.example.objectmapper.dto;

import java.util.Objects;

public class AnySetterNested {
    private String name;
    private int size;

    public AnySetterNested() {
    }

    public AnySetterNested(String name, int size) {
        this.name = name;
        this.size = size;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AnySetterNested that = (AnySetterNested) o;
        return size == that.size &&
                Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, size);
    }

    @Override
    public String toString() {
        return "AnySetterNested{" +
                "name='" + name + '\'' +
                ", size=" + size +
                '}';
    }
}
