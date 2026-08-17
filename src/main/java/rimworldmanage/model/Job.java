package rimworldmanage.model;

public enum Job {
    SHOOTING("사격"),
    CONSTRUCTION("건설"),
    MINING("채굴"),
    COOKING("요리"),
    MEDICAL("의료"),
    RESEARCH("연구");

    // 사용자에게 보여줄 한글 이름
    private final String description;

    Job(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }



}
