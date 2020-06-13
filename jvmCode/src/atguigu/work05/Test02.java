package atguigu.work05;

public class Test02 {
    public static void main(String []args){
        MyDate date1=new MyDate();
        MyDate date2=new MyDate();
        MyDate date3=new MyDate();

        date1.year=1994;
        date1.month=05;
        date1.date=13;

        date2.year=2020;
        date2.month=05;
        date2.date=23;

        date3.year=2020;
        date3.month=11;
        date3.date=23;

        System.out.println("出生年月"+date1.year+" "+date1.month+" "+date1.date);
        System.out.println("来尚硅谷的日期"+date1.year+" "+date1.month+" "+date1.date);
        System.out.println("毕业日期"+date1.year+" "+date1.month+" "+date1.date);

    }
}
