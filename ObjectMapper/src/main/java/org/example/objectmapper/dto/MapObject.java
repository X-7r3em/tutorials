package org.example.objectmapper.dto;

import java.util.Objects;

public class MapObject {
    private String key;
    private String name;
    private int age;

    public MapObject() {
    }

    public MapObject(String key, String name, int age) {
        this.key = key;
        this.name = name;
        this.age = age;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MapObject mapObject = (MapObject) o;
        return age == mapObject.age &&
                Objects.equals(key, mapObject.key) &&
                Objects.equals(name, mapObject.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(key, name, age);
    }

    @Override
    public String toString() {
        return "MapObject{" +
                "key='" + key + '\'' +
                ", name='" + name + '\'' +
                ", age='" + age + '\'' +
                '}';
    }
}
