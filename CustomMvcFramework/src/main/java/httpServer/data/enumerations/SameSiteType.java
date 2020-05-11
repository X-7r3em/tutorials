package httpServer.data.enumerations;

public enum SameSiteType {
    NONE(1),
    LAX(2),
    STRICT(3);

    private final int value;

    SameSiteType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
