package models.cases.fieldEnums;

public enum Behavior {
    NOTSET("Not set"),
    POSITIVE("Positive"),
    NEGATIVE("Negative"),
    DESTRUCTIVE("Destructive");

    private final String name;

    Behavior(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
