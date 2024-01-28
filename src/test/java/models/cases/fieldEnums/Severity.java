package models.cases.fieldEnums;

public enum Severity {
    NOTSET("Not set"),
    BLOCKER("Blocker"),
    CRITICAL("Critical"),
    MAJOR("Major"),
    NORMAL("Normal"),
    MINOR("Minor"),
    TRIVIAL("Trivial");

    private final String name;

    Severity(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
