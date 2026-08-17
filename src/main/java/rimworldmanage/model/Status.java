package rimworldmanage.model;

public enum Status {
    HEALTHY("정상"),
    INJURED("부상"),
    SICK("질병"),
    MENTAL_BREAK("정신 이상");

    private final String description;

    Status(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

}
