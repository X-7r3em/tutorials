package org.example.objectmapper.dto;

import java.util.Objects;

public class TransactionBody {
    private String nestedId;
    private int nestedSize;
    private TransactionBodyInner inner;

    public TransactionBody() {
    }

    public TransactionBody(String nestedId, int nestedSize, TransactionBodyInner inner) {
        this.nestedId = nestedId;
        this.nestedSize = nestedSize;
        this.inner = inner;
    }

    public String getNestedId() {
        return nestedId;
    }

    public void setNestedId(String nestedId) {
        this.nestedId = nestedId;
    }

    public int getNestedSize() {
        return nestedSize;
    }

    public void setNestedSize(int nestedSize) {
        this.nestedSize = nestedSize;
    }

    public TransactionBodyInner getInner() {
        return inner;
    }

    public void setInner(TransactionBodyInner inner) {
        this.inner = inner;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TransactionBody that = (TransactionBody) o;
        return nestedSize == that.nestedSize &&
                Objects.equals(nestedId, that.nestedId) &&
                Objects.equals(inner, that.inner);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nestedId, nestedSize, inner);
    }

    @Override
    public String toString() {
        return "TransactionBody{" +
                "nestedId='" + nestedId + '\'' +
                ", nestedSize=" + nestedSize +
                ", inner=" + inner +
                '}';
    }
}
