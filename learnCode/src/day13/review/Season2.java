package day13.review;

public enum  Season2 {
    SPRING("春天","春天呀"),
    SUMMER("夏天","夏天呀"),
    AUTUMN("秋天","秋天呀"),
    WINTER("冬天","冬天呀");

    private String seasonName;
    private String seasonDesc;

    Season2() {
    }

    Season2(String seasonName, String seasonDesc) {
        this.seasonName = seasonName;
        this.seasonDesc = seasonDesc;
    }

    public String getSeasonName() {
        return seasonName;
    }

    public void setSeasonName(String seasonName) {
        this.seasonName = seasonName;
    }

    public String getSeasonDesc() {
        return seasonDesc;
    }

    public void setSeasonDesc(String seasonDesc) {
        this.seasonDesc = seasonDesc;
    }

    @Override
    public String toString() {
        return "Season2{" +
                "seasonName='" + seasonName + '\'' +
                ", seasonDesc='" + seasonDesc + '\'' +
                '}';
    }
}
