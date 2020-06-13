package day13;

public class morningTest {
    public static void main(String[] args) {
        SingleHungry singleHungry=SingleHungry.getInstance();
        SingleHungry singleHungry1=SingleHungry.getInstance();
        System.out.println(singleHungry==singleHungry1);
        SingleLazy singleLazy=SingleLazy.getInstance();
        SingleLazy singleLazy1=SingleLazy.getInstance();
        System.out.println(singleLazy==singleLazy1);
    }
}
class Test {
    public Test() {
        Inner s1 = new Inner();
        s1.a = 10;
        Inner s2 = new Inner();
        s2.a = 20;
        Test.Inner s3 = new Test.Inner();
        System.out.println(s3.a);
    }

    class Inner {
        public int a = 5;
    }

    public static void main(String[] args) {
        Test t = new Test();
        Inner r = t.new Inner();
        System.out.println(r.a);
    }
}
class SingleHungry{
    private static SingleHungry hungry=new SingleHungry();

    private SingleHungry(){}

    public static SingleHungry getInstance(){
        return hungry;
    }
}

class SingleLazy{
    private static SingleLazy singleLazy=null;

    private SingleLazy(){}

    public static SingleLazy getInstance(){
        if(singleLazy==null){
            singleLazy=new SingleLazy();
        }
        return singleLazy;
    }
}

interface testA{
    default public void say(){
        System.out.println("hello");
    }
    public static void show(){
        System.out.println("静态方法也继承了");
    }
}
class TestC{
    public void say(){
        System.out.println("hdh");
    }
}
class TestB implements testA{
    public static void show(){
        System.out.println("没有被继承");
    }
    public static void main(String[] args) {
        TestB.show();
    }
}

