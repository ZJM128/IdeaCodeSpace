package atguigu.day05;

/**
 * 练习1：
 * 编写一个Student类，包含name、gender、age、id、score属性，分别为String、String、int、int、double类型。
 * 类中声明一个say方法，返回String类型，方法返回信息中包含所有属性值。
 * 在另一个TestStudent类中的main方法中，创建Student对象，并访问say方法和所有属性，并将调用结果打印输出。
 */
public class TestStudent {
    public static void main(String[]args){
        Student student=new Student();
        student.id=12;
        student.name="张三";
        student.gender="男";
        student.age=25;
        student.score=99;

        String result=student.say();
        System.out.println(result);
    }
}

class Student{
    public String name;
    public String gender;
    public int age;
    public int id;
    public double score;

    public String say(){
        return id+" "+name+" "+gender+" "+age+" "+score;
    }

}