package atguigu.work05;

public class Test05 {
    public static void main(String[] args) {
        MyDate D=new MyDate();
        D.year=2020;
        D.month=1;
        D.date=25;
        D.puls(5,5,31);
        System.out.println(D.year+" 年"+D.month+"月 "+D.date+"日");
    }
}
