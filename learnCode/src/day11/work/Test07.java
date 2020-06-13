package day11.work;

public class Test07 {
    public static void main(String[] args) {
        Son son = new Son();
    }
}
class Father2{
    static{
        System.out.println("（1）父类的静态代码块");
    }
    {
        System.out.println("（2）父类的非静态代码块");
    }
    Father2(){
        System.out.println("（3）父类的无参构造");
    }
}
class Son extends Father2{
    static{
        System.out.println("（4）子类的静态代码块");
    }
    {
        System.out.println("（5）子类的非静态代码块");
    }
    Son(){
        System.out.println("（6）子类的无参构造");
    }
}