package app.model;

public class ParamTestNestedObject {
    private int number;
    private Integer secondNumber;

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Integer getSecondNumber() {
        return secondNumber;
    }

    public void setSecondNumber(Integer secondNumber) {
        this.secondNumber = secondNumber;
    }

    @Override
    public String toString() {
        return "ParamTestNestedObject{" +
                "number=" + number +
                ", secondNumber=" + secondNumber +
                '}';
    }
}
