package atguigu.work05;

public class Test01 {
    public static void main(String []args){
        Employee e=new Employee();
        Employee e2=new Employee();
        e.no=1;
        e.name="张三";
        e.age=23;
        e.salary=10000.2;

        e2.no=2;
        e2.name="李四";
        e2.age=26;
        e2.salary=12000.6;

        System.out.println("员工1:的编号"+e.no+"姓名:"+e.name+"年龄:"+e.age+"薪资"+e.salary );
        System.out.println("员工2:的编号"+e2.no+"姓名:"+e2.name+"年龄:"+e2.age+"薪资"+e2.salary );
    }
}
