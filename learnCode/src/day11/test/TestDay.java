package day11.test;

public class TestDay {
    public static void main(String[] args) {
        MyDate myDate=new MyDate("1日","10月","2014年");
        MyDate myDate2=new MyDate("1日","10月","2014年");
        System.out.println(myDate.equals(myDate2));
        System.out.println(myDate);
        System.out.println(myDate2);

    }
}
