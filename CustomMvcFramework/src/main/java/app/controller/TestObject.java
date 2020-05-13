package app.controller;

import java.util.List;

public class TestObject {
    private String name;
    private boolean isTrue;
    private List<Integer> numbers;

    public TestObject(String name, boolean isTrue, List<Integer> numbers) {
        this.name = name;
        this.isTrue = isTrue;
        this.numbers = numbers;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isTrue() {
        return isTrue;
    }

    public void setTrue(boolean aTrue) {
        isTrue = aTrue;
    }

    public List<Integer> getNumbers() {
        return numbers;
    }

    public void setNumbers(List<Integer> numbers) {
        this.numbers = numbers;
    }
}
