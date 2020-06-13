package day10.test;

/*
*@Description:编写Student类，其中包含Computer类型的属性，提供构造器及相关方法，以及say方法用于自我描述。
编写Test类，在main方法中创建Student对象，调用say方法打印输出结果。
*@author:zhijm
*@Date:2020/6/5 16:53
*/
public class Manager {
    private String name;
    private int age;
    private Computer computer;

    public Manager() {
    }

    public Manager(String name, int age, Computer computer) {
        this.name = name;
        this.age = age;
        this.computer = computer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Computer getComputer() {
        return computer;
    }

    public void setComputer(Computer computer) {
        this.computer = computer;
    }

    public void say(){
        System.out.println("我叫:"+name+" "+age+"岁"+" 我有一台:"+computer.getDetail());
    }


}
