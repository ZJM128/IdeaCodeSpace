package day10.warkbooks;
/*
*@Description:属性没有多态性 ,采用的是就近原则
*@author:zhijm
*@Date:2020/6/5 19:06
*/
public class PoymorphismTest2 {
    public static void main(String[] args) {
        Father father=new Son();
        System.out.println(father.age);
        father.show();
    }
}
class Father{
    int age =56;
    public void show(){
        System.out.println("父亲的年龄:"+age);
    }
}
class Son extends Father{
    int age =25;
    public void show(){
        System.out.println("儿子的年龄:"+age);
    }
}