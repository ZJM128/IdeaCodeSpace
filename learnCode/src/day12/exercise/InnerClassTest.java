package day12.exercise;

public class InnerClassTest {
    public static void main(String[] args) {
        // 普通成员内部类创建的格式
        // 外部类名.内部类名 内部类对象名=外部类名.new 内部类名(参数列表);
        Person03 person03=new Person03();
        Person03.Computer computer=person03.new Computer();
        System.out.println(computer.getName());

        // 静态内部类
        // 格式 外部类名.内部类名 对象名=new 外部类名.内部类名()
        Person03.Mobile mobile=new Person03.Mobile();
        mobile.show();
    }
}
// 成员内部类
class Person03{
    private String name="李白";
    private int age=12;

    public Person03() {
    }

    public Person03(String name, int age) {
        this.name = name;
        this.age = age;
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

    public class Computer{
        private String name="联想";

        public Computer() {
        }

        public Computer(String name) {
            this.name = name;
        }

        public String getName() {
            System.out.println(age);
            System.out.println("内部类的name"+name);
            System.out.println("Computer 内部类对象的name:"+this.name);

            System.out.println("Person03的name"+Person03.this.name);
            System.out.println(Person03.this.name);
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

    }
    Head head=new Head();
    private String firstName=head.fristName;
    /**
     * 实现对类的隐藏
     */
    private class Head{
        private String fristName;

    }

    static class Mobile{
        public void show(){
            System.out.println("静态内部类中的方法");
        }
    }
}