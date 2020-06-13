package atguigu.work05;

/**
 * 周一Monday
 * 周二Tuesday
 * 周三Wednesday
 * 周四Thursday
 * 周五Friday
 * 周六Saturday
 * 周日Sunday
 */
public class DateCommonsTools {
    public String getWeekName(int week){
        switch(week){
            case 1:
                return "Monday";
            case 2:
                return "Tuesday";
            case 3:
                return "Wednesday";
            case 4:
                return "Thursday";
            case 5:
                return "Friday";
            case 6:
                return "Saturday";
            case 7:
                return "Sunday";
        }
        return null;
    }

    public String getMonthName(int month){
        switch(month){
            case 1:
                return "January";
            case 2:
                return "February";
            case 3:
                return "March";
            case 4:
                return "April";
            case 5:
                return "May";
            case 6:
                return "June";
            case 7:
                return "July";
            case 8:
                return "August";
            case 9:
                return "September";
            case 10:
                return "October";
            case 11:
                return "November";
            case 12:
                return "December";
            default:
                return null;
        }
    }

    public int getTotalDaysOfMonths(int year,int month){
        int days=0;
        if(month==1||month==3||month==5||month==7|| month==8 || month==10|| month==12){
            days+=31;
        }else if(month==4 || month ==6 ||month==9 || month ==11){
            days+=30;
        }else if(month ==2){
            if((year%4==0&& year %100!=0)|| year%400==0){
                days+=29;
            }else{
                days+=28;
            }
        }
        return days;
    }

    public int getTotalDaysOfYear(int year){
        if((year%4==0&& year%100!=0)||year % 400==0){
            return 366;
        }else{
            return 365;
        }
    }

    public boolean isLeanYear(int year){
        return year%4==0&&year%4!=0||year%400==0;
    }
}
