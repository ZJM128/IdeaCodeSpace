package day11.work;

public class Test16 {
    public static void main(String[] args) {
        Employee employee=new Employee("12","礼拜",25,12000);
        Employee employee1=new Programmer("13","李白",26,120030);
        Employee employee2=new Designer("14","白居易",26,120030,1.36);
        Employee employee3=new Architect("15","李贺",26,120030,52,56);
        System.out.println(employee.getInfo());
        System.out.println(employee1.getInfo());
        System.out.println(employee2.getInfo());
        System.out.println(employee3.getInfo());

    }
}
