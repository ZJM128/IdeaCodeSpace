package atguigu.work05;

public class MyDate {
    int year;
    int month;
    int date;

    public boolean isLeapYear(){
        if((year%4==0 && year%100!=0)| year%400==0){
            return true;
        }
        return false;
    }

    public void set(int y,int m,int d){
        year=y;
        month=m;
        date=d;
    }

    public void puls(int years,int months,int dates){
        year+=years;
        month+=months;
        date+=dates;
        if(month>12){
            year+=(month/12);
            month=month%12;
        }
        while(true){
            if(month==1 || month==3 || month==5 || month==7 || month==8 | month==10){
                if(date>31){
                    month++;
                    date-=31;
                }else{
                    break;
                }
            }else if(month==4 || month==6 || month==9 || month==11){
                if(date>30){
                    month++;
                    date-=30;
                }else{
                    break;
                }
            }else if(month==12){
                if(date>31){
                    date-=31;
                    month=1;
                    year++;
                }else{
                    break;
                }
            }else if(month==2){
                if(year%4==0 && year%100!=0 || year%400==0){
                    if(date>29){
                        date -= 29;
                        month++;
                    }else{
                        break;
                    }
                }else{
                    if(date>28){
                        date-=28;
                        month++;
                    }else{
                        break;
                    }
                }
            }
        }

    }
}
