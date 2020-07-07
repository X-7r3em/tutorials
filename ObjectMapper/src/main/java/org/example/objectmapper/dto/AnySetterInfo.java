package org.example.objectmapper.dto;

import java.util.Objects;

public class AnySetterInfo {
    private String infoId;
    private String brand;
    private AnySetterNested nested;

    public AnySetterInfo() {
    }

    public AnySetterInfo(String infoId, String brand, AnySetterNested nested) {
        this.infoId = infoId;
        this.brand = brand;
        this.nested = nested;
    }

    public String getInfoId() {
        return infoId;
    }

    public void setInfoId(String infoId) {
        this.infoId = infoId;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public AnySetterNested getNested() {
        return nested;
    }

    public void setNested(AnySetterNested nested) {
        this.nested = nested;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AnySetterInfo that = (AnySetterInfo) o;
        return Objects.equals(infoId, that.infoId) &&
                Objects.equals(brand, that.brand) &&
                Objects.equals(nested, that.nested);
    }

    @Override
    public int hashCode() {
        return Objects.hash(infoId, brand, nested);
    }

    @Override
    public String toString() {
        return "AnySetterInfo{" +
                "infoId='" + infoId + '\'' +
                ", brand='" + brand + '\'' +
                ", nested=" + nested +
                '}';
    }
}
