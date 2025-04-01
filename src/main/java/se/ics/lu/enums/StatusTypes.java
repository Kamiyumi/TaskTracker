package se.ics.lu.enums;

public enum StatusTypes {
    FAILED("FAILED"),
    PROGRESS("IN PROGRESS"),
    COMPLETED("COMPLETED");

    private final String value;

    StatusTypes(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
