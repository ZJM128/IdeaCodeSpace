package day14.morning;
/*
*@Description:试说出内部类的好处，并写个局部内部类例子
*@author:zhijm
*@Date:2020/6/10 8:25
*/
public class InnerClass {
    public static void main(String[] args) {
        Person person=getPerson();
        person.say();

    }
    public static Person getPerson(){
        return new Person() {
            @Override
            public void say() {
                System.out.println("good morning");
            }
        };
        /*return ()->{
            System.out.println("hello");
        };*/
    }

}
interface Person{
    void say();
}