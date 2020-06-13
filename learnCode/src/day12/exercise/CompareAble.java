package day12.exercise;

public interface CompareAble {
    default void compare(Apple apple, Apple b){
        if(apple.getSize()>b.getSize()){
            System.out.println(apple);
        }else if(apple.getSize()<b.getSize()){
            System.out.println(b);
        }else{
            System.out.println(apple);
            System.out.println(b);
        }
    }


}
interface Compare{
    void compare(Apple apple, Apple b);
}
class CompareBig implements CompareAble{

}

class CompareColor implements CompareAble{
    public void compare(Apple apple, Apple b){
        System.out.println("挑红的");
        if("红色".equals(apple.getColor())){
            System.out.println(apple);
        }
        if("红色".equals(b.getColor())){
            System.out.println(b);
        }
    }
}

class Worker{
    public void pickApple(CompareAble compareAble, Apple apple,Apple apple2){
        compareAble.compare(apple,apple2);
    }

   /* public CompareAble getCompareAble(){
        return ()->{};
    }*/
    public static void main(String[] args) {
        //Worker w = new Worker();
        Apple a1 = new Apple(5, "青色");
        Apple a2 = new Apple(3, "红色");

        //w.pickApple(new CompareBig(), a1, a2);
       // w.pickApple(new CompareColor(), a1, a2);
        /*new CompareAble(){
            public void compare(Apple apple, Apple b){
                System.out.println("挑红的");
                if("红色".equals(apple.getColor())){
                    System.out.println(apple);
                }
                if("红色".equals(b.getColor())){
                    System.out.println(b);
                }
            }
        }.compare(a1,a2);*/

        Compare compare=(Apple a, Apple b)->{System.out.println("挑红的");
            if("红色".equals(a.getColor())){
                System.out.println(a);
            }
            if("红色".equals(b.getColor())){
                System.out.println(b);
            }};
        compare.compare(a1,a2);

        new CompareAble(){
            public void compare(Apple apple, Apple b){
                System.out.println("挑大的");
                if(apple.getSize()>b.getSize()){
                    System.out.println(apple);
                }else if(apple.getSize()<b.getSize()){
                    System.out.println(b);
                }else{
                    System.out.println(apple);
                    System.out.println(b);
                }
            }
        }.compare(a1,a2);
    }
}