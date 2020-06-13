package day13.review;

public class Season1 {
    private String seasonName;
    private String seasonDesc;
    // 类的内部创建对象
    public static final Season1 SPRING=new Season1("春天","春暖花开");
    public  static final Season1 SUMMER=new Season1("夏天","夏天热");
    public static final Season1 AUTUMN= new Season1("秋天","秋天天气爽");
    public static final Season1 WINTER=new Season1("冬天","冬天寒冷");


    public Season1() {
    }

    public Season1(String seasonName, String seasonDesc) {
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
        return "Season1{" +
                "seasonName='" + seasonName + '\'' +
                ", seasonDesc='" + seasonDesc + '\'' +
                '}';
    }
}
