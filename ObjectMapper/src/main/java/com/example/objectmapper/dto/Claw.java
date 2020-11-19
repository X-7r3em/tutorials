package com.example.objectmapper.dto;

public class Claw {
    private int size;

    public Claw() {
    }

    public Claw(int size) {
        this.size = size;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return "Claw{" +
                "size=" + size +
                '}';
    }
}
