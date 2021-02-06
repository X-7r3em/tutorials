package app.model;

public class ParamTestObject {
    private ParamTestNestedObject paramTestNestedObject;
    private String text;
    private char letter;
    private boolean isValid;

    public ParamTestNestedObject getParamTestNestedObject() {
        return paramTestNestedObject;
    }

    public void setParamTestNestedObject(ParamTestNestedObject paramTestNestedObject) {
        this.paramTestNestedObject = paramTestNestedObject;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public char getLetter() {
        return letter;
    }

    public void setLetter(char letter) {
        this.letter = letter;
    }

    public boolean isValid() {
        return isValid;
    }

    public void setValid(boolean valid) {
        isValid = valid;
    }

    @Override
    public String toString() {
        return "ParamTestObject{" +
                "paramTestNestedObject=" + paramTestNestedObject +
                ", text='" + text + '\'' +
                ", letter=" + letter +
                ", isValid=" + isValid +
                '}';
    }
}
