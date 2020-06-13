package atguigu.day05;
/*
改写Dog类，name属性使用缺省初始值，age和weight属性使用显式初始值1和10。
在TestDog类的main方法中，创建两个Dog对象，分别调用两对象的say方法，将调用结果打印输出。
继续在main方法中，将两个Dog对象的name属性分别设为“京叭 ”和“吉娃娃”，第二个对象的体重设为8。再分别调用两对象的say方法，将调用结果打印输出。
根据输出结果，理解由同一类创建的不同对象的属性的独立性。
 */

/**
 * // 类的属性:成员变量 实例变量 有默认值
 * //① 基本类型 byte short int -->0
 * //long =>0L
 * //float==>0.0F  double==>0.0D
 * // char==>'u000'
 * // ②引用类型 都为null
 * // 类 --String
 * // 接口
 * // 数组
 */
public class DogDemo {
    public static void main(String []args){

        Dog1 dog1=new Dog1();
        Dog1 dog2=new Dog1();
        dog1.name="京巴";
        dog2.name="吉娃娃";
        dog2.weight=8;
        dog1.say();
        dog2.say();
    }
}

class Dog1{


    String name;//缺省初始值
    int age=1;
    int weight=10;
    public void say(){
        System.out.println(name+" "+age + " "+ weight  );
    }
}