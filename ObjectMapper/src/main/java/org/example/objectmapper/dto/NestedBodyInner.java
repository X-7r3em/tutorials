package org.example.objectmapper.dto;

import java.util.Objects;

public class NestedBodyInner {
    private int innerInfo;

    public NestedBodyInner() {
    }

    public NestedBodyInner(int innerInfo) {
        this.innerInfo = innerInfo;
    }

    public int getInnerInfo() {
        return innerInfo;
    }

    public void setInnerInfo(int innerInfo) {
        this.innerInfo = innerInfo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NestedBodyInner that = (NestedBodyInner) o;
        return innerInfo == that.innerInfo;
    }

    @Override
    public int hashCode() {
        return Objects.hash(innerInfo);
    }

    @Override
    public String toString() {
        return "TransactionBodyInner{" +
                "innerInfo=" + innerInfo +
                '}';
    }
}
