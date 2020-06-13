package atguigu.day05;

/**
 * 练习2：
 * 编写一个Dog类，包含name、age、weight属性
 * 类中声明一个say方法，返回String类型，方法返回信息中包含所有属性值。
 * 在另一个TestDog类中的main方法中，创建Dog对象，并访问say方法和所有属性，将调用结果打印输出。
 */
public class TestDog {
    public static void main(String []args){
        Dog dog=new Dog();
        dog.age=2;
        dog.name="tony";
        dog.weight=26;
        String result=dog.say();
        System.out.println(result);
    }
}
class Dog{
    public String name;
    public int age;
    public double weight;

    public String say(){
        return name+" "+age+" "+weight;
    }
}