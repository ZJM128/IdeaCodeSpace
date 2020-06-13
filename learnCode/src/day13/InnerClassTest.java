package day13;

public class InnerClassTest {
    public static void main(String[] args) {
        show();
    }

    public static void show(){
        int num=0;
        class inner{
            public void show(){
                System.out.println(num+4);// 使用了同级别的局部变量,该变量要声明为final的,
                // 因为num出栈了,但是内部类的对象还存在的话,就不指定不了,所以不允许修改
            }
        }

        new inner().show();
    }

    public Flyer getFlyer(){
        class Brird implements Flyer{
            public void fly(){
                System.out.println("i can fly");
            }
        }
        Flyer f=new Brird();
        return f;
    }

    public Flyer getFlyer1(){
        Flyer flyer = new Flyer() {
            @Override
            public void fly() {
                System.out.println(" l can fly too");
            }
        };
        return flyer;
    }

    public Flyer getFlyer3(){
        return new Flyer() {
            @Override
            public void fly() {
                System.out.println("I can fly too too");
            }
        };
    }

    public Flyer getFlyer4(){
        return ()-> System.out.println(" i not can fly");
    }
}
interface Flyer{
    void fly();
}
class Brird implements Flyer{
    public void fly(){
        System.out.println("i can fly");
    }
}