package models.cases.fieldEnums;

public enum Automation {
    MANUAL("Manual"),
    AUTOMATED("Automated");

    private final String name;

    Automation(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
