package models.cases.fieldEnums;

public enum Priority {
    NOTSET("Not set"),
    HIGH("High"),
    MEDIUM("Medium"),
    LOW("Low");

    private final String name;

    Priority(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
