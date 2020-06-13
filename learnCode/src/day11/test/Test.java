package day11.test;

public class Test {
   /* public static void main(String[] args) {
        Father f = new Son();
        System.out.println(f.x);// Son.x =0 Son.x =30 20
    }*/
   public static void main(String[] args) {
       Base b1 = new Base();//base:100
       Base b2 = new Sub();// base:100 sub:70
   }
}
class Father{
    int x = 10;
    public Father(){
        this.print();
        x = 20;
    }
    public void print(){
        System.out.println("Father.x = " + x);
    }
}
class Son extends Father{
    int x = 30;
    public Son(){
        this.print();
        x = 40;
    }
    public void print(){
        System.out.println("Son.x = " + x);
    }
}

class Base {
    Base() {
        method(100);
    }

    public void method(int i) {
        System.out.println("base : " + i);
    }
}

class Sub extends Base {
    Sub() {
        super.method(70);
    }

    public void method(int j) {
        System.out.println("sub : " + j);
    }
}