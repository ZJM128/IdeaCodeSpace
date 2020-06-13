package day13.review;

public enum Month {
    JANUARY(1,"一月的一年的开始"),
    FEBRUARY(2,"二月"),
    MARCH(3,"3月"),
    APRIL(4,"四月"),
    MAY(5,"五月"),
    JUNE(6,"六月"),
    JULY(7,"七月"),
    AUGUST(8,"八月"),
    SEPTEMBER(9,"九月"),
    OCTOBER(10,"十月"),
    NOVEMBER(11,"十一月"),
    december(12,"十二月");
    private int value;
    private String description;

    Month(int value, String description) {
        this.value = value;
        this.description = description;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public static Month getByValue(int value){
        return Month.values()[value-1];
    }

    @Override
    public String toString() {
        return this.value+"--->"+this.description;
    }
}
