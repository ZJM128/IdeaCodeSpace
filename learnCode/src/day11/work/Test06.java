package day11.work;

public class Test06 {
        public static void main(String[] args) {
            Sub s = new Sub();
        }
}
class Base{
    Base(){
        method(100);
    }
    {
        System.out.println("base");
    }
    public void method(int i){
        System.out.println("base : " + i);
    }
}
class Sub extends Base{
    Sub(){
        super.method(70);
    }
    {
        System.out.println("sub");
    }
   public void method(int j){
        System.out.println("sub : " + j);
    }
}