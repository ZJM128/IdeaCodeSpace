package day12.single;

public class SingleTest {

    public static void main(String[] args) {
        SingleHungry hungry=SingleHungry.getInstance();
        SingleHungry hungry1=SingleHungry.getInstance();
        if(hungry==hungry1){
            System.out.println("相同");
        }

        SingleLazy singleLazy=SingleLazy.getInstance();
        SingleLazy singleLazy1=SingleLazy.getInstance();
        System.out.println(singleLazy==singleLazy1);
    }
}
class SingleHungry{

    // 创建静态的私有对象
    private static SingleHungry singleHungry=new SingleHungry() ;

    // 私有化构造器 不外面创建对象
    private SingleHungry(){}

    // 提供公共的方法 返回对象
    public static SingleHungry getInstance(){
        return singleHungry;
    }
}

class SingleLazy{
    // 声明静态的是有对象变量
    private static SingleLazy singleLazy=null;

    // 私有化构造器 不让外面创建对象
    private SingleLazy(){}

    // 提供公共方法,如果对象不为空,则创建对象
    public static SingleLazy getInstance(){
        if(singleLazy==null){
            singleLazy=new SingleLazy();
        }
        return singleLazy;
    }
}