package day11.test;

public class MyDate {
    private String day;
    private String month;
    private String year;

    public MyDate() {
    }

    public MyDate(String day, String month, String year) {
        this.day = day;
        this.month = month;
        this.year = year;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }
    public boolean equals(Object object){
        if(object==this){
            return true;
        }
        if(object instanceof MyDate){
            if(this.year.equals(((MyDate) object).year)){
                if(this.month.equals(((MyDate) object).month)){
                    if(this.day.equals(((MyDate) object).day)){
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public String toString(){
        return year+month+day;
    }
}
