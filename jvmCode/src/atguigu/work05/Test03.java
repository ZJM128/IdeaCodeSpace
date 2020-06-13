package atguigu.work05;

public class Test03 {
    public static void main(String[] args) {
        MyDate d1 = new MyDate();
        MyDate d2 = new MyDate();
        d1.year = 2020;
        d2.year = 2020;
        d1.month = 12;
        d2.month = 1;
        d1.date = 2;
        d2.date = 6;
        Citizen citizen1 = new Citizen();
        Citizen citizen2 = new Citizen();
        citizen1.name = "张三";
        citizen1.birthDay = d1;
        citizen1.idCard = "122445";

        citizen2.name = "李四";
        citizen2.birthDay = d2;
        citizen2.idCard = "56489";

        System.out.println("姓名" + citizen1.name + " 出生年月" + citizen1.birthDay.year + " " + citizen1.birthDay.month + " " + citizen1.birthDay.date + " 身份证" + citizen1.idCard);
        System.out.println("姓名" + citizen2.name + " 出生年月" + citizen2.birthDay.year + " " + citizen2.birthDay.month + " " + citizen2.birthDay.date + " 身份证" + citizen2.idCard);
    }
}
