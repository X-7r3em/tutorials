package com.example.unittesting.dtos;

import java.util.Objects;

public class Odd {
    private String name;
    private int amount;
    private int index;

    public Odd() {
    }

    public Odd(String name, int amount, int index) {
        this.name = name;
        this.amount = amount;
        this.index = index;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Odd odd = (Odd) o;
        return amount == odd.amount &&
                index == odd.index &&
                Objects.equals(name, odd.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, amount, index);
    }

    @Override
    public String toString() {
        return "Odd{" +
                "name='" + name + '\'' +
                ", amount=" + amount +
                ", index=" + index +
                '}';
    }
}
